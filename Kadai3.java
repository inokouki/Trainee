package jp.co.iccom.ino_kouki.calculate_sales;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Kadai3 {
	private static BufferedReader storebr, goodsbr, calcbr, calcfr;

	public static void main(String[] args) throws IOException{
		List<String> storelist = new ArrayList<String>();
		List<String> storelistname = new ArrayList<String>();
		List<String> goodslist = new ArrayList<String>();
		List<String> goodslistname = new ArrayList<String>();
		HashMap<String, String> storecodemap = new HashMap<String, String>();
		HashMap<String, String> goodscodemap = new HashMap<String, String>();

		//支店ファイル読み込み//
		try{
			String storearray[] = null;
			File dir = null;

			if(args.length == 1){
				dir = new File(args[0]);
			} else {
				System.err.println("予期せぬエラーが発生しました");
				return;
			}

			//コマンドライン引数がディレクトリ以外のとき//
			if(!dir.isDirectory()){
				System.err.println("予期せぬエラーが発生しました");
				return;
			}

			//コマンドライン引数のパスのディレクトリ内のbranch.lstから読み込み//
			BufferedReader storebr =
					new BufferedReader(new FileReader(args[0] + File.separator + "\\branch.lst"));
			String storestr = storebr.readLine();

			//ハッシュマップ作成//
			ArrayList<String> store = new ArrayList<String>();
			HashMap<String, String> storemap = new HashMap<String, String>();

			//支店コード、支店名に分解//
			while(storestr != null){
				store.add(storestr);
				storearray = storestr.split(",");

				if(!storearray[0].matches("\\d{3}")){
					System.err.println("支店定義ファイルのフォーマットが不正です");
					storebr.close();
					return;
				}

				storemap.put(storearray[0],storearray[1]);
				storelist.add(storearray[0]);
				storelistname.add(storearray[1]);
				storestr = storebr.readLine();
			}
			storebr.close();
		}

		catch(FileNotFoundException e){
			System.err.println("支店定義ファイルが存在しません");
			return;
		}

		catch(ArrayIndexOutOfBoundsException e){
			System.err.println("支店定義ファイルのフォーマットが不正です");
			return;
		}
		finally{
			if(storebr != null) {storebr.close();}
		}

		//商品定義ファイル読み込み//
		try{
			String goodsarray[] = null;

			BufferedReader goodsbr =
					new BufferedReader(new FileReader(args[0] + File.separator + "\\commodity.lst"));
			String goodsstr = goodsbr.readLine();

			//ハッシュマップ作成//
			ArrayList<String> goods = new ArrayList<String>();
			HashMap<String, String> goodsmap = new HashMap<String, String>();

			//商品コード、商品名に分解//
			while(goodsstr != null){
				goods.add(goodsstr);
				goodsarray = goodsstr.split(",");
				goodsmap.put(goodsarray[0],goodsarray[1]);

				if(!goodsarray[0].matches("SFT\\d{5}")){
					System.err.println("商品定義ファイルのフォーマットが不正です");
					goodsbr.close();
					return;
				}
				goodslist.add(goodsarray[0]);
				goodslistname.add(goodsarray[1]);
				goodsstr = goodsbr.readLine();
			}
		}

		catch(FileNotFoundException e){
			System.err.println("商品定義ファイルが存在しません");
			return;
		}

		catch(ArrayIndexOutOfBoundsException e){
			System.err.println("商品定義ファイルのフォーマットが不正です");
			return;
		}
		finally{
			if(goodsbr != null) {goodsbr.close();}
		}

		//集計//
		try{
			int count = 0, filenametemp = 0, indexnum;
			List<String> data = new ArrayList<String>();
			List<String> sucnum = new ArrayList<String>();
			String[] tempstr = null;
			long valuetemp = 0;
			String valuestr;

			//コマンドライン引数のディレクトリをオープン//
			File calcfile = new File(args[0]);

			//フィルタクラスで[8桁数字.rcd]のファイル形式のみfilesに入れる//
			File[] filearray = calcfile.listFiles();
			String[]files = calcfile.list(new Filter());

			//sucnumにファイル名である8桁の数字を格納//
			for(int i = 0 ; i < files.length ; i++){
				for(int j = 0 ; j < 2 ; j++){
					if(j == 0){
						tempstr = files[i].split("\\.");
						sucnum.add(tempstr[j]);
					}
				}

				filenametemp = Integer.parseInt(sucnum.get(i));

				//売り上げファイル名が連番になっているか、ディレクトリか、どうか検査//
				if(filenametemp != i + 1 || filearray[i].isDirectory()){
					System.err.println("売り上げファイル名が連番になっていません");
					return;
				}
			}

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
						calcbr.close();
						return;
					}
					count++;
				}
				calcbr.close();
			}

			for(int i = 0 ; i < count ; i++){
				String strkeytemp = null;
				long longkeytemp;;

				//支店コード//
				if(i % 3 == 0){
					//int型に変換//
					valuetemp = Long.parseLong(data.get(i+2));

					//存在しないときindexnumに-1が入る//
					indexnum = storelist.indexOf(data.get(i));
					if(indexnum == -1){
						System.err.println(Arrays.asList(files[i/3]) + "の支店コードが不正です");
						return;
					}

					//既にマップが存在しているかどうか//
					if(storecodemap.containsKey(data.get(i))){
						strkeytemp = storecodemap.get(data.get(i));
						longkeytemp = Long.parseLong(strkeytemp);
						longkeytemp = longkeytemp + valuetemp;
						strkeytemp = String.valueOf(longkeytemp);

						//合計売り上げが11桁以上のときエラー処理//
						if(strkeytemp.matches("^\\d{11,}")){
							System.err.println("合計金額が10桁を超えました");
							return;
						}
						storecodemap.put(data.get(i), strkeytemp);
					} else {
						valuestr = String.valueOf(valuetemp);

						//合計売り上げが11桁以上のときエラー処理//
						if(valuestr.matches("^\\d{11,}")){
							System.err.println("合計金額が10桁を超えました");
							return;
						}
						storecodemap.put(data.get(i), valuestr);
					}
				}

				//商品コード//
				if(i % 3 == 1){
					//存在しないときindexnumに-1が入る//
					indexnum = goodslist.indexOf(data.get(i));
					if(indexnum == -1){
						return;
					}

					//既にマップが存在しているかどうか//
					if(goodscodemap.containsKey(data.get(i))){
						strkeytemp = goodscodemap.get(data.get(i));
						longkeytemp = Long.parseLong(strkeytemp);
						longkeytemp = longkeytemp + valuetemp;
						strkeytemp = String.valueOf(longkeytemp);

						//合計売り上げが11桁以上のときエラー処理//
						if(strkeytemp.matches("^\\d{11,}")){
							System.err.println("合計金額が10桁を超えました");
							return;
						}

						goodscodemap.put(data.get(i), strkeytemp);
					} else {
						valuestr = String.valueOf(valuetemp);

						//合計売り上げが11桁以上のときエラー処理//
						if(valuestr.matches("^\\d{11,}")){
							System.err.println("合計金額が10桁を超えました");
							return;
						}

						goodscodemap.put(data.get(i), valuestr);
					}
				}
			}
		}

		catch(Exception e){
			System.err.println("予期せぬエラーが発生しました");
			return;
		}

		finally{
			if(calcbr != null) {calcbr.close();}
			if(calcfr != null) {calcfr.close();}
		}

		//ファイル出力//
		try{
			//支店別、出力ファイル名を作成、ファイルオブジェクトの生成//
			String outputStoreFileName = args[0] + File.separator + "\\branch.out";
			PrintWriter storepw = new PrintWriter(outputStoreFileName);

			//商品別、出力ファイル名を作成、ファイルオブジェクトの生成//
			String outputGoodsFileName = args[0] + File.separator + "\\commodity.out";
			PrintWriter goodspw = new PrintWriter(outputGoodsFileName);

			//支店別の売り上げ確認//
			for(int i = 0 ; i < storelist.size() ; i++){
				if(storecodemap.get(storelist.get(i)) == null){
					storecodemap.put(storelist.get(i), "0");
				}
			}

			//支店別売り上げの並び替え//
			List<Map.Entry<String,String>> entries =
		              new ArrayList<Map.Entry<String,String>>(storecodemap.entrySet());
		        Collections.sort(entries, new Comparator<Map.Entry<String,String>>() {

		        	long e1 = 0, e2 = 0;

		            @Override
		            public int compare(
		                  Entry<String,String> entry1, Entry<String,String> entry2) {
		            	e1 = Long.parseLong(entry2.getValue());
		            	e2 = Long.parseLong(entry1.getValue());
		            	if(e1 > e2){
		            		return 1;
		            	} else if(e1 == e2){
		            		return 0;
		            	} else {
		            		return -1;
		            	}
		            }
		        });

			//支店別の売り上げの並び替え終了後//
		        for(Entry<String, String> s : entries) {
		        	int number = 0;

		            number = Integer.parseInt(s.getKey()) - 1;
			        storepw.println(s.getKey() + "," + storelistname.get(number) + "," + s.getValue());
		        }
		        System.out.println("支店ファイル出力完了");
		        storepw.close();

				//商品別の売り上げ確認//
				for(int i = 0 ; i < goodslist.size() ; i++){
					if(goodscodemap.get(goodslist.get(i)) == null){
						goodscodemap.put(goodslist.get(i), "0");
					}
				}

				//商品別売り上げの並び替え//
				List<Map.Entry<String,String>> goodsentries =
			              new ArrayList<Map.Entry<String,String>>(goodscodemap.entrySet());
			        Collections.sort(goodsentries, new Comparator<Map.Entry<String,String>>() {

			        	long e1 = 0, e2 = 0;
			            @Override
			            public int compare(
			                  Entry<String,String> entry1, Entry<String,String> entry2) {
			            	e1 = Long.parseLong(entry2.getValue());
			            	e2 = Long.parseLong(entry1.getValue());
			            	if(e1 > e2){
			            		return 1;
			            	} else if(e1 == e2){
			            		return 0;
			            	} else {
			            		return -1;
			            	}
			            }
			        });

			        //商品別の売り上げの並び替え終了後//
			        for(Entry<String, String> t : goodsentries) {
			        	String strnumber = null;
			        	int intnumber = 0;

			            if(t.getKey().matches("SFT\\d{5}$")){
			            	strnumber = t.getKey().substring(4-1);
			            	intnumber = Integer.parseInt(strnumber)-1;
			            }
				        goodspw.println(t.getKey() + "," + goodslistname.get(intnumber) + "," + t.getValue());
			        }
			        System.out.println("商品ファイル出力完了");
			        goodspw.close();
		}

		catch(Exception e){
			System.err.println("予期せぬエラーが発生しました");
			return;
		}

		finally{
			System.out.println();
			System.out.println("売り上げ集計システム:処理終了");
		}
	}
}

class Filter implements FilenameFilter{
	public boolean accept(File dir, String name) {
        //拡張子が"rcd"と一致、かつファイル名が8桁の数字のとき取り出す
		return name.matches("^\\d{8}.rcd$");
	}
}
