package jp.co.iccom.ino_kouki.calculate_sales;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Kadai3 {
	private static BufferedReader storebr, goodsbr, calcbr, calcfr, storebw, earnbr, earnfr;
	public static HashMap<String, String> storecodemap = new HashMap<String, String>();
	public static HashMap<String, String> goodscodemap = new HashMap<String, String>();
	public static String path = null;

	public static void main(String[] args) throws IOException{
		List<String> storelist = new ArrayList<String>();
		List<String> storelistname = new ArrayList<String>();
		List<String> goodslist = new ArrayList<String>();
		List<String> goodslistname = new ArrayList<String>();
		HashMap<String, String> goodsmap = new HashMap<String, String>();
		HashMap<String, String> storemap = new HashMap<String, String>();

		//支店ファイル読み込み//
		try{
			String storearray[] = null;
			File dir;

			//コマンドライン引数の数が1つ以外のときエラー//
			if(args.length == 1){
				dir = new File(args[0]);
				path = args[0];
			} else {
				System.out.println("予期せぬエラーが発生しました");
				return;
			}

			//コマンドライン引数がディレクトリ以外のときエラー//
			if(!dir.isDirectory()){
				System.out.println("予期せぬエラーが発生しました");
				return;
			}

			//コマンドライン引数のパスのディレクトリ内のbranch.lstから読み込み//
			storebr = FileRead("branch.lst");
			String storestr = storebr.readLine();

			ArrayList<String> store = new ArrayList<String>();

			//支店コード、支店名に分解//
			while(storestr != null){
				store.add(storestr);
				storearray = storestr.split("," , 2);

				//数字3桁ではない、カンマが1つ以外の、ときエラー処理//
				if(!storearray[0].matches("\\d{3}") || storearray.length != 2){
					System.out.println("支店定義ファイルのフォーマットが不正です");
					storebr.close();
					return;
				}

				storecodemap.put(storearray[0], "0");
				storemap.put(storearray[0], storearray[1]);

				storelist.add(storearray[0]);
				storelistname.add(storearray[1]);

				storestr = storebr.readLine();
			}
			storebr.close();
		}

		catch(FileNotFoundException e){
			System.out.println("支店定義ファイルが存在しません");
			return;
		}

		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("支店定義ファイルのフォーマットが不正です");
			return;
		}

		catch(IOException e){
			System.out.println("予期せぬエラーが発生しました");
			return;
		}

		finally{
			if(storebr != null) {storebr.close();}
		}

		//商品定義ファイル読み込み//
		try{
			String goodsarray[] = null;

			goodsbr = FileRead("commodity.lst");
			String goodsstr = goodsbr.readLine();

			//ハッシュマップ作成//
			ArrayList<String> goods = new ArrayList<String>();

			//商品コード、商品名に分解//
			while(goodsstr != null){
				goods.add(goodsstr);
				goodsarray = goodsstr.split("," , 2);

				//商品コードが英数字8文字ではない、カンマが1つ以外の、ときエラー処理//
				if(!goodsarray[0].matches("[a-zA-Z0-9]{8}") || goodsarray.length != 2){
					System.out.println("商品定義ファイルのフォーマットが不正です");
					goodsbr.close();
					return;
				}

				goodscodemap.put(goodsarray[0], "0");
				goodsmap.put(goodsarray[0], goodsarray[1]);

				goodslist.add(goodsarray[0]);
				goodslistname.add(goodsarray[1]);
				goodsstr = goodsbr.readLine();
			}
		}

		catch(FileNotFoundException e){
			System.out.println("商品定義ファイルが存在しません");
			return;
		}

		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("商品定義ファイルのフォーマットが不正です");
			return;
		}

		catch(IOException e){
			System.out.println("予期せぬエラーが発生しました");
			return;
		}
		finally{
			if(goodsbr != null) {goodsbr.close();}
		}

		//集計//
		try{
			int filenametemp = 0, indexnum;
			List<String> sucnum = new ArrayList<String>();
			String[] tempstr = null;
			long valuetemp = 0;

			//コマンドライン引数のディレクトリをオープン//
			File calcfile = new File(args[0]);

			//フィルタクラスで[8桁数字.rcd]のファイル形式のみfilesに入れる//
			File[] filearray = calcfile.listFiles();
			String[]files = calcfile.list(new Filter());

			//sucnumにファイル名である8桁の数字を格納//
			for(int i = 0 ; i < files.length ; i++){
				for(int j = 0 ; j < 2 ; j++){
					if(j == 0){
						tempstr = files[i].split("\\.", 2);
						sucnum.add(tempstr[j]);
					}
				}

				filenametemp = Integer.parseInt(sucnum.get(i));

				//売り上げファイル名が連番になっているか、ディレクトリか、どうか検査//
				if(filenametemp != i + 1 || filearray[i].isDirectory()){
					System.out.println("売上ファイル名が連番になっていません");
					return;
				}
			}

			//数字8桁.rcdファイルの中身が3行以外はエラー//
			for(int i = 0 ; i < files.length ; i++){
				String earnline = null;
				ArrayList<String> earn = new ArrayList<String>();

				earnbr = FileRead(files[i]);

				while((earnline = earnbr.readLine()) != null){
					earn.add(earnline);
				}

				if(earn.size() != 3){
					System.out.println(files[i] + "のフォーマットが不正です");
					return;
				}
				earnbr.close();
			}

			//フィルタを通ったファイルのみ、配列dataに情報を格納して出力する//
			for(int i = 0; i < files.length ; i++){
				int icount = 0;
				String strkeytemp = null, line, storekey = null, goodskey = null, before = null;

				calcbr = FileRead(files[i]);

				while((line = calcbr.readLine()) != null){
					if(icount > 3){
						System.out.println(files[i] + "のフォーマットが不正です");
						calcbr.close();
						return;
					}

					//icount=0,支店コードのときの集計//
					if(icount == 0){
						storekey = line;


						//存在しないときindexnumに-1が入る//
						indexnum = storelist.indexOf(storekey);
						if(indexnum == -1){
							System.out.println(files[i] + "の支店コードが不正です");
							calcfr.close();
							calcbr.close();
							return;
						}
					}

					//icount=1,商品コードのときの集計//
					if(icount == 1){
						goodskey = line;

						//存在しないときindexnumに-1が入る//
						indexnum = goodslist.indexOf(goodskey);
						if(indexnum == -1){
							System.out.println(files[i] + "の商品コードが不正です");
							calcfr.close();
							calcbr.close();
							return;
						}
					}

					//icount=2,売り上げ数値のときの集計処理//
					if(icount == 2){
						//売り上げをString型からlong型に変換//
						valuetemp = Long.parseLong(line);

						//支店コードで既にマップが存在しているかどうか//
						if(storecodemap.containsKey(storekey)){
							before = storecodemap.get(storekey);
							strkeytemp = add(before, valuetemp);

							//合計売り上げが11桁以上のときエラー処理//
							if(strkeytemp.matches("^\\d{11,}")){
								System.out.println("合計金額が10桁を超えました");
								calcfr.close();
								calcbr.close();
								return;
							}
							storecodemap.put(storekey, strkeytemp);
						} else {
							System.out.println(files[i] + "の支店コードが不正です");
							calcfr.close();
							calcbr.close();
							return;
						}

						//商品コードで既にマップが存在しているかどうか//
						if(goodscodemap.containsKey(goodskey)){
							before = goodscodemap.get(goodskey);
							strkeytemp = add(before, valuetemp);

							//合計売り上げが11桁以上のときエラー処理//
							if(strkeytemp.matches("^\\d{11,}")){
								System.out.println("合計金額が10桁を超えました");
								calcfr.close();
								calcbr.close();
								return;
							}
							goodscodemap.put(goodskey, strkeytemp);
						} else {
							System.out.println(files[i] + "の商品コードが不正です");
							calcfr.close();
							calcbr.close();
							return;
						}
					}
					icount++;
				}

				if(icount != 3){
						System.out.println(files[i] + "のフォーマットが不正です");
						calcbr.close();
						return;
				}
				calcbr.close();
			}
		}

		catch(IOException e){
			System.out.println("予期せぬエラーが発生しました");
		}

		finally{
			if(calcbr != null) {calcbr.close();}
			if(calcfr != null) {calcfr.close();}
			if(earnbr != null) {earnbr.close();}
			if(earnfr != null) {earnfr.close();}
		}

		//ファイル出力//
		try{
			int storeloop = storecodemap.size(), goodsloop = goodscodemap.size();

			//支店別,商品別の出力ファイルを生成する準備をする//
			BufferedWriter storebw = readyWriteFile("branch.out");
			BufferedWriter goodsbw = readyWriteFile("commodity.out");

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
			        storebw.write(s.getKey() + "," + storemap.get(s.getKey()) + "," + s.getValue());
			        if(storeloop > 1){
 				        storebw.newLine();
 				        storeloop--;
 			        }
		        }
				storebw.close();

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
				        goodsbw.write(t.getKey() + "," + goodsmap.get(t.getKey()) + "," + t.getValue());
				        if(goodsloop > 1){
 					        goodsbw.newLine();
 					        goodsloop--;
 				        }
			        }
					goodsbw.close();
		}

		catch(IOException e){
			System.out.println("予期せぬエラーが発生しました");
			return;
		}

		finally{
			if(storebw != null) {storebw.close();}
		}
	}

	//引数(mapの今の値, 加算する値)から、2つの値を加算し、加算後の数値を返すメソッド//
	public static String add(String beforevalue, long additionalvalue){
		String after = null;
		long temp = 0, longkeytemp = 0;

		temp = Long.parseLong(beforevalue);
		longkeytemp = additionalvalue + temp;
		after = String.valueOf(longkeytemp);

		return after;
	}

	//引数(読込するファイル名)から、BufferedReader型を返却するメソッド//
	public static BufferedReader FileRead(String FileName){
		FileReader fr = null;
		try {
			fr = new FileReader(path + File.separator + FileName);
		} catch (FileNotFoundException e) {
			System.out.println("予期せぬエラーが発生しました");
		}
		BufferedReader br = new BufferedReader(fr);
		return br;
	}

	//引数(出力するファイル名)から、BufferedWriter型を返却するメソッド//
	public static BufferedWriter readyWriteFile(String FileName){
		FileWriter fw = null;
		try {
			fw = new FileWriter(path + File.separator + FileName);
		} catch (IOException e) {
			System.out.println("予期せぬエラーが発生しました");
		}
		BufferedWriter bw = new BufferedWriter(fw);

		return bw;
	}
}

class Filter implements FilenameFilter{
	public boolean accept(File dir, String name) {
        //拡張子が"rcd"と一致、かつファイル名が8桁の数字のとき取り出す
		return name.matches("^\\d{8}.rcd$");
	}
}