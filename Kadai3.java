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
	public static HashMap<String, String> goodsmap = new HashMap<String, String>();
	public static HashMap<String, String> storemap = new HashMap<String, String>();
	public static List<String> storelist = new ArrayList<String>();
	public static List<String> storelistname = new ArrayList<String>();
	public static List<String> goodslist = new ArrayList<String>();
	public static List<String> goodslistname = new ArrayList<String>();
	public static String path = null;

	public static void main(String[] args) throws IOException{
		//支店ファイル読み込み//
		try{
			String storearray[] = null;
			File dir;

			if(args.length == 1){
				dir = new File(args[0]);
				path = args[0];}
			else {
				System.out.println("予期せぬエラーが発生しました");
				return;}

			if(!dir.isDirectory()){
				System.out.println("予期せぬエラーが発生しました");
				return;}

			storebr = ReadFile("branch.lst");
			String storestr = storebr.readLine();

			//支店コード、支店名に分解//
			while(storestr != null){
				storearray = storestr.split("," , 2);

				//数字3桁ではない、カンマが1つ以外の、ときエラー処理//
				if(!storearray[0].matches("\\d{3}") || storearray.length != 2){
					System.out.println("支店定義ファイルのフォーマットが不正です");
					return;}
				storestr = PutDataToMap(storecodemap, storemap, storearray, storelist, storelistname, storebr);
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

			goodsbr = ReadFile("commodity.lst");
			String goodsstr = goodsbr.readLine();

			//商品コード、商品名に分解//
			while(goodsstr != null){
				goodsarray = goodsstr.split("," , 2);

				//商品コードが英数字8文字ではない、またはカンマが1つ以外のときエラー処理//
				if(!goodsarray[0].matches("[a-zA-Z0-9]{8}") || goodsarray.length != 2){
					System.out.println("商品定義ファイルのフォーマットが不正です");
					goodsbr.close();
					return;}
				goodsstr = PutDataToMap(goodscodemap, goodsmap, goodsarray, goodslist, goodslistname, goodsbr);
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
				tempstr = files[i].split("\\.", 2);
				sucnum.add(tempstr[0]);

				filenametemp = Integer.parseInt(sucnum.get(i));

				//売り上げファイル名が連番になっているか、ディレクトリか、どうか検査//
				if(filenametemp != i + 1 || filearray[i].isDirectory()){
					System.out.println("売上ファイル名が連番になっていません");
					return;}
			}

			//数字8桁.rcdファイルの中身が3行以外はエラー//
			for(int i = 0 ; i < files.length ; i++){
				String earnline = null;
				ArrayList<String> earn = new ArrayList<String>();

				earnbr = ReadFile(files[i]);

				while((earnline = earnbr.readLine()) != null){
					earn.add(earnline);
				}

				if(earn.size() != 3){
					System.out.println(files[i] + "のフォーマットが不正です");
					return;}
				earnbr.close();
			}

			//フィルタを通ったファイルのみ、配列dataに情報を格納して出力する//
			for(int i = 0; i < files.length ; i++){
				int icount = 0;
				String strkeytemp = null, line, storekey = null, goodskey = null, before = null;

				calcbr = ReadFile(files[i]);

				while((line = calcbr.readLine()) != null){
					if(icount > 3){
						System.out.println(files[i] + "のフォーマットが不正です");
						return;}

					//icount=0,支店コードのときの集計//
					if(icount == 0){
						storekey = line;

						//存在しないときindexnumに-1が入る//
						indexnum = storelist.indexOf(storekey);
						if(indexnum == -1){
							System.out.println(files[i] + "の支店コードが不正です");
							return;}
					}

					//icount=1,商品コードのときの集計//
					if(icount == 1){
						goodskey = line;

						//存在しないときindexnumに-1が入る//
						indexnum = goodslist.indexOf(goodskey);
						if(indexnum == -1){
							System.out.println(files[i] + "の商品コードが不正です");
							return;}
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
								return;}
							storecodemap.put(storekey, strkeytemp);
						} else {
							System.out.println(files[i] + "の支店コードが不正です");
							return;}

						//商品コードで既にマップが存在しているかどうか//
						if(goodscodemap.containsKey(goodskey)){
							before = goodscodemap.get(goodskey);
							strkeytemp = add(before, valuetemp);

							//合計売り上げが11桁以上のときエラー処理//
							if(strkeytemp.matches("^\\d{11,}")){
								System.out.println("合計金額が10桁を超えました");
								return;}
							goodscodemap.put(goodskey, strkeytemp);
						} else {
							System.out.println(files[i] + "の商品コードが不正です");
							return;}
					}
					icount++;
				}

				if(icount != 3){
						System.out.println(files[i] + "のフォーマットが不正です");
						return;}
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

			BufferedWriter storebw = ReadyWriteFile("branch.out");
			BufferedWriter goodsbw = ReadyWriteFile("commodity.out");

			//支店別売り上げの並び替え//
			List<Map.Entry<String,String>> storeentries =
		              new ArrayList<Map.Entry<String,String>>(storecodemap.entrySet());
			SortEntryMap(storeentries);

		    //支店別の売り上げの並び替え終了後//
			for(Entry<String, String> s : storeentries) {
				storebw.write(s.getKey() + "," + storemap.get(s.getKey()) + "," + s.getValue());
				if(storeloop > 1){
					storebw.newLine();
					storeloop--;}
		        }
			storebw.close();

			//商品別売り上げの並び替え//
			List<Map.Entry<String,String>> goodsentries =
					new ArrayList<Map.Entry<String,String>>(goodscodemap.entrySet());

			SortEntryMap(goodsentries);
			//商品別の売り上げの並び替え終了後//
			for(Entry<String, String> t : goodsentries) {
				goodsbw.write(t.getKey() + "," + goodsmap.get(t.getKey()) + "," + t.getValue());
				if(goodsloop > 1){
					goodsbw.newLine();
					goodsloop--;}
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
	public static BufferedReader ReadFile(String FileName){
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
	public static BufferedWriter ReadyWriteFile(String FileName){
		FileWriter fw = null;
		try {
			fw = new FileWriter(path + File.separator + FileName);
		} catch (IOException e) {
			System.out.println("予期せぬエラーが発生しました");
		}
		BufferedWriter bw = new BufferedWriter(fw);

		return bw;
	}

	//引数(売り上げ格納map,[コード・名称]連結map,分割後文字列の配列,コードリスト、具体名リスト, BufferedReader変数)//
	public static String PutDataToMap(HashMap<String,String> initializationmap, HashMap<String,String> normalmap,
			String[] array, List<String> list, List<String> listname, BufferedReader br){
		String str = null;

		initializationmap.put(array[0], "0");
		normalmap.put(array[0], array[1]);
		list.add(array[0]);
		listname.add(array[1]);

		try {
			str = br.readLine();
		} catch (IOException e) {
			System.out.println("予期せぬエラーです");
		}
		return str;
	}

	//引数(mapのentry)から並び替えを行うメソッド//
	public static void SortEntryMap(List<Map.Entry<String,String>> entries){
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
	}
}

class Filter implements FilenameFilter{
	public boolean accept(File dir, String name) {
        //拡張子が"rcd"と一致、かつファイル名が8桁の数字のとき取り出す
		return name.matches("^\\d{8}.rcd$");
	}
}