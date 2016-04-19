package jp.co.iccom.ino_kouki.calculate_sales;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Kadai3 {
	public static void main(String[] args) throws IOException{
		//エラーの種類を判断する//
		int er = 0;
		try{
			String store1[] = null, store2[] = null;

			//コマンドライン引数をpathに代入//
			String path = ".";
			if(args.length != 0){
				path = args[0];
			}

			//パスをファイル型に変換//
			File dir = new File(path);

			//コマンドライン引数がディレクトリ以外のとき//
			if(!dir.isDirectory()){
				System.err.println("引数にディレクトリパスが入っていません");
				System.exit(0);
			}

			//コマンドライン引数のパスのディレクトリ内のbranch.lstから読み込み//
			BufferedReader br1 =
					new BufferedReader(new FileReader(args[0] + File.separator + "branch.lst"));
			String str1 = br1.readLine();

			//ハッシュマップ作成//
			ArrayList<String> shop1 = new ArrayList<String>();
			HashMap<String, String> Map1 = new HashMap<String, String>();

			//支店コード、支店名に分解//
			while(str1 != null){
				er = 1;
				shop1.add(str1);

				store1 = str1.split(",");

				Map1.put(store1[0],store1[1]);

				str1 = br1.readLine();
				System.out.println(store1[0] + "," + store1[1]);
			}
			br1.close();
			System.out.println();

			//商品定義//
			BufferedReader br2 =
					new BufferedReader(new FileReader(args[0] + File.separator + "commodity.lst"));
			String str2 = br2.readLine();

			//ハッシュマップ作成//
			ArrayList<String> shop2 = new ArrayList<String>();
			HashMap<String, String> Map2 = new HashMap<String, String>();

			//商品コード、商品名に分解//
			while(str2 != null){
				er = 2;
				shop2.add(str2);
				store2 = str2.split(",");

				Map2.put(store2[0],store2[1]);

				str2 = br2.readLine();
				System.out.println(store2[0] + "," + store2[1]);
			}
			br2.close();
			er = 0;
			System.out.println();

			//集計//

			//コマンドライン引数のディレクトリをオープン//
			File dir3 = new File(args[0]);

			//フィルタクラスで探す//
			String []files = dir3.list(new Filter());

			//全て出力//
			for(int i=0; i<files.length; ++i){
				System.out.println(files[i]);
			}


		}

		//branch.lstが見つからなかったとき//
		catch(FileNotFoundException e){
			System.err.println("支店定義ファイルが存在しません");
			System.exit(0);
		}

		//入出力の際に問題があったとき//
		catch(IOException e){
			System.out.println(e);
			System.exit(0);
		}

		//支店定義ファイルに区切りの","がなかったとき//
		catch(ArrayIndexOutOfBoundsException e){
			if(er == 1){
			System.err.println("支店定義ファイルのフォーマットが不正です");
			System.err.println("branch.lstファイルを確認してください");
			System.exit(0);
			} else if(er == 2){
				System.err.println("商品定義ファイルのフォーマットが不正です");
				System.err.println("commodity.lstファイルを確認してください");
				System.exit(0);
			}
		}
	}
}

class Filter implements FilenameFilter{
	public boolean accept(File dir, String name) {

		        int index = name.lastIndexOf(".");//拡張子の"."を探す

		        //"."以下の文字列を取り出して全て小文字に
		        String ext = name.substring(index+1).toLowerCase();

		        //拡張子が"rcd"と一致すれば取り出す
		        if(ext.equals("rcd") == true) {return true;}

		        //それ以外のファイルはリストアップしない
		        return false;
		    }
}
