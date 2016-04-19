package jp.co.iccom.ino_kouki.calculate_sales;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Kadai3 {
	public static void main(String[] args) throws IOException{
		try{
			int i = 1;
			String store[];

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
			BufferedReader br =
					new BufferedReader(new FileReader(args[0] + File.separator + "branch.lst"));
			String str = br.readLine();

			//ハッシュマップ作成//
			ArrayList<String> shop = new ArrayList<String>();
			HashMap<String, String> Map = new HashMap<String, String>();

			//支店コード、支店名に分解//
			while(str != null){
				shop.add(str);
				store = str.split(",");

				Map.put(store[0],store[1]);

				str = br.readLine();

				i++;
			}
			//ハッシュマップのキーを元に、要素名を出力//
			System.out.println(Map.keySet());
			System.out.println(Map.values());

			br.close();
		}

		//branch.lstが見つからなかったとき//
		catch(FileNotFoundException e){
			System.err.println("支店定義ファイルが存在しません");
		}

		//入出力の際に問題があったとき//
		catch(IOException e){
			System.out.println(e);
		}

		//支店定義ファイルに区切りの","がなかったとき//
		catch(ArrayIndexOutOfBoundsException e){
			System.err.println("支店定義ファイルのフォーマットが不正です");
		}
	}
}
