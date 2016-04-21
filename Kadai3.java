package jp.co.iccom.ino_kouki.calculate_sales;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Kadai3 {
	private static BufferedReader storebr, goodsbr, calcbr, calcfr;

	public static void main(String[] args) throws IOException{
		//エラーの種類を判断する//
		int er = 0;

		try{
			String storearray[] = null, goodsarray[] = null;
			int count = 0, valuetemp = 0;
			List<String> data = new ArrayList<String>();
			List<String> sucnum = new ArrayList<String>();
			String[] tempstr = null;
			int filenametemp = 0;
			int storetempnum = 0;
			String valuestr;
			HashMap<String, String> storecodemap = new HashMap<String, String>();
			HashMap<String, String> goodscodemap = new HashMap<String, String>();

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
			BufferedReader storebr =
					new BufferedReader(new FileReader(args[0] + File.separator + "branch.lst"));
			String storestr = storebr.readLine();

			//ハッシュマップ作成//
			ArrayList<String> store = new ArrayList<String>();
			HashMap<String, String> storemap = new HashMap<String, String>();

			//支店コード、支店名に分解//
			while(storestr != null){
				er = 1;
				store.add(storestr);

				storearray = storestr.split(",");

				storemap.put(storearray[0],storearray[1]);

				storestr = storebr.readLine();
				System.out.println(storearray[0] + "," + storearray[1]);
			}
			storebr.close();
			System.out.println();

			//商品定義//
			BufferedReader goodsbr =
					new BufferedReader(new FileReader(args[0] + File.separator + "commodity.lst"));
			String goodsstr = goodsbr.readLine();

			//ハッシュマップ作成//
			ArrayList<String> goods = new ArrayList<String>();
			HashMap<String, String> goodsmap = new HashMap<String, String>();

			//商品コード、商品名に分解//
			while(goodsstr != null){
				er = 2;
				goods.add(goodsstr);
				goodsarray = goodsstr.split(",");

				goodsmap.put(goodsarray[0],goodsarray[1]);

				goodsstr = goodsbr.readLine();
				System.out.println(goodsarray[0] + "," + goodsarray[1]);
			}
			goodsbr.close();
			er = 0;
			System.out.println();

			//集計//

			//コマンドライン引数のディレクトリをオープン//
			File calcfile = new File(args[0]);

			//フィルタクラスで[8桁数字.rcd]のファイル形式のみfilesに入れる//
			String[]files = calcfile.list(new Filter());
			System.out.println("フィルター後: " + Arrays.asList(files));

			//sucnumにファイル名である8桁の数字を格納//
			for(int k = 0 ; k < files.length ; k++){
				for(int l = 0 ; l < 2 ; l++){
					if(l == 0){
						tempstr = files[k].split("\\.");
						sucnum.add(tempstr[l]);
					}
				}

				filenametemp = Integer.parseInt(sucnum.get(k));
				System.out.println("filenametemp: " + filenametemp);

				//売り上げファイル名が連番になっているかどうか検査//
				if(filenametemp != k + 1){
					System.err.println("売り上げファイル名が連番になっていません");
					System.exit(0);
				}
			}
			System.out.println("ファイル名: " + sucnum);
			System.out.println();


			//フィルタを通ったファイルのみ、配列dataに情報を格納して出力する//

			for(int i = 0; i<files.length ; i++){
				int icount = 0;
				String line;
				FileReader calcfr = new FileReader(args[0] + File.separator + files[i]);
				BufferedReader calcbr =
						new BufferedReader(calcfr);
				while((line = calcbr.readLine()) != null){
					if(icount < 3){
						data.add(line);
						icount++;
					} else {
						System.err.println(files[i] + "のフォーマットが不正です");
						System.exit(0);
					}
					count++;
				}
			}
			System.out.println("処理回数 " + count + ":" + data);
			System.out.println();

			for(int j = 0 ; j < count ; j++){
				String strkeytemp = null;
				int intkeytemp;

				//支店コード//
				if(j % 3 == 0){
					System.out.println("j%3=0");
					//int型に変換//
					storetempnum = Integer.parseInt(data.get(j));
					valuetemp = Integer.parseInt(data.get(j+2));

					System.out.println("storetempnum: " + storetempnum);
					System.out.println("valuetemp: " + valuetemp);

					//入力売り上げが10桁以上のときエラー処理//
					if(valuetemp > 999999999){
						System.err.println("合計金額が10桁を超えました");
						System.exit(0);
					}

					//既にマップが存在しているかどうか//
					if(storecodemap.containsKey(data.get(j))){
						strkeytemp = storecodemap.get(data.get(j));
						intkeytemp = Integer.parseInt(strkeytemp);
						intkeytemp = intkeytemp + valuetemp;
						strkeytemp = String.valueOf(intkeytemp);
						storecodemap.put(data.get(j), strkeytemp);
						System.out.println("キーが見つかりました: " + intkeytemp);
					} else {
						valuestr = String.valueOf(valuetemp);
						storecodemap.put(data.get(j), valuestr);
						System.out.println("キーが見つかりませんでした。マップを作成します");
					}
					System.out.println();
				}

				//商品コード//
				if(j % 3 == 1){
					System.out.println("j%3=1");

					System.out.println("storetempnum: " + storetempnum);
					System.out.println("valuetemp: " + valuetemp);

					//入力売り上げが10桁以上のときエラー処理//
					if(valuetemp > 999999999){
						System.err.println("合計金額が10桁を超えました");
						System.exit(0);
					}

					//既にマップが存在しているかどうか//
					if(goodscodemap.containsKey(data.get(j))){
						strkeytemp = goodscodemap.get(data.get(j));
						intkeytemp = Integer.parseInt(strkeytemp);
						intkeytemp = intkeytemp + valuetemp;
						strkeytemp = String.valueOf(intkeytemp);
						goodscodemap.put(data.get(j), strkeytemp);
						System.out.println("商品コード: " + data.get(j) + " 合計金額: " + strkeytemp);
					} else {
						valuestr = String.valueOf(valuetemp);
						goodscodemap.put(data.get(j), valuestr);
						System.out.println("商品コード: " + data.get(j) + " 金額: " + valuestr + "をセットしました");
					}

					System.out.println();
				}
			}

			if(calcbr != null) {calcbr.close();}
			if(calcfr != null) {calcfr.close();}
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
			} else if(er == 2){
				System.err.println("商品定義ファイルのフォーマットが不正です");
			}
		}

		catch(NumberFormatException e){
		}

		finally{
			//close処理//
			if(storebr != null) {storebr.close();}
			if(goodsbr != null) {goodsbr.close();}
			if(calcbr != null) {calcbr.close();}
			if(calcfr != null) {calcfr.close();}
		}
	}
}

class Filter implements FilenameFilter{
	public boolean accept(File dir, String name) {
        //拡張子が"rcd"と一致、かつファイル名が8桁の数字のとき取り出す
		return name.matches("^\\d{8}.rcd$");
    }
}
