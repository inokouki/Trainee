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
	public static String path = null;

	public static void main(String[] args) throws IOException{
		try{
			String storearray[] = null;
			File dir;

			if(args.length == 1){
				dir = new File(args[0]);
				path = args[0];
			} else {
				System.out.println("予期せぬエラーが発生しました");
				return;
			}

			if(!dir.isDirectory()){
				System.out.println("予期せぬエラーが発生しました");
				return;
			}

			storebr = readFile("branch.lst");
			String storestr = storebr.readLine();

			while(storestr != null){
				storearray = storestr.split("," , 2);

				if(!storearray[0].matches("\\d{3}") || storearray.length != 2){
					System.out.println("支店定義ファイルのフォーマットが不正です");
					return;
				}
				storestr = putDataToMap(storecodemap, storemap, storearray, storebr);
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
			if(storebr != null) storebr.close();
		}

		try{
			String goodsarray[] = null;

			goodsbr = readFile("commodity.lst");
			String goodsstr = goodsbr.readLine();

			while(goodsstr != null){
				goodsarray = goodsstr.split("," , 2);

				if(!goodsarray[0].matches("[a-zA-Z0-9]{8}") || goodsarray.length != 2){
					System.out.println("商品定義ファイルのフォーマットが不正です");
					goodsbr.close();
					return;
				}
				goodsstr = putDataToMap(goodscodemap, goodsmap, goodsarray, goodsbr);
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
			if(goodsbr != null) goodsbr.close();
		}

		try{
			int filenametemp = 0;
			List<String> sucnum = new ArrayList<String>();
			String[] tempstr = null;
			long valuetemp = 0;

			File calcfile = new File(args[0]);
			File[] filearray = calcfile.listFiles();
			String[]files = calcfile.list(new Filter());

			for(int i=0; i<files.length; i++){
				ArrayList<String> earn = new ArrayList<String>();
				int icount = 0;
				String strkeytemp=null, line, storekey=null, goodskey=null, before=null, earnline=null;

				tempstr = files[i].split("\\.", 2);
				sucnum.add(tempstr[0]);
				filenametemp = Integer.parseInt(sucnum.get(i));

				if(filenametemp != i+1 || filearray[i].isDirectory()){
					System.out.println("売上ファイル名が連番になっていません");
					return;
				}

				earnbr = readFile(files[i]);
				while((earnline = earnbr.readLine()) != null){
					earn.add(earnline);
				}
				if(earn.size() != 3){
					System.out.println(files[i] + "のフォーマットが不正です");
					return;
				}
				earnbr.close();

				calcbr = readFile(files[i]);
				while((line = calcbr.readLine()) != null){
					if(icount > 3){
						System.out.println(files[i] + "のフォーマットが不正です");
						return;
					}
					if(icount == 0){
						storekey = line;

						if(!storecodemap.containsKey(storekey)){
							System.out.println(files[i] + "の支店コードが不正です");
							return;
						}
					}
					if(icount == 1){
						goodskey = line;

						if(!goodscodemap.containsKey(goodskey)){
							System.out.println(files[i] + "の商品コードが不正です");
							return;
						}
					}
					if(icount == 2){
						valuetemp = Long.parseLong(line);

						if(storecodemap.containsKey(storekey)){
							before = storecodemap.get(storekey);
							strkeytemp = add(before, valuetemp);

							if(strkeytemp.matches("^\\d{11,}")){
								System.out.println("合計金額が10桁を超えました");
								return;
							}
							storecodemap.put(storekey, strkeytemp);
						} else {
							System.out.println(files[i] + "の支店コードが不正です");
							return;
						}
						if(goodscodemap.containsKey(goodskey)){
							before = goodscodemap.get(goodskey);
							strkeytemp = add(before, valuetemp);

							if(strkeytemp.matches("^\\d{11,}")){
								System.out.println("合計金額が10桁を超えました");
								return;}
							goodscodemap.put(goodskey, strkeytemp);
						} else {
							System.out.println(files[i] + "の商品コードが不正です");
							return;
						}
					}
					icount++;
				}
				if(icount != 3){
						System.out.println(files[i] + "のフォーマットが不正です");
						return;
				}
			}
		}
		catch(IOException e){
			System.out.println("予期せぬエラーが発生しました");
		}
		finally{
			if(calcbr != null) calcbr.close();
			if(calcfr != null) calcfr.close();
			if(earnbr != null) earnbr.close();
			if(earnfr != null) earnfr.close();
		}

		try{
			int storeloop = storecodemap.size(), goodsloop = goodscodemap.size();
			List<Map.Entry<String,String>> storeentries =
					new ArrayList<Map.Entry<String,String>>(storecodemap.entrySet());
			List<Map.Entry<String,String>> goodsentries =
					new ArrayList<Map.Entry<String,String>>(goodscodemap.entrySet());

			BufferedWriter storebw = readyWriteFile("branch.out");
			BufferedWriter goodsbw = readyWriteFile("commodity.out");
			sortEntryMap(storeentries);
			sortEntryMap(goodsentries);

			for(Entry<String, String> s : storeentries) {
				writeFile(s, storeloop, storebw, storemap);
				storeloop--;
		    }
			storebw.close();

			for(Entry<String, String> t : goodsentries) {
				writeFile(t, goodsloop, goodsbw, goodsmap);
				goodsloop--;
		    }
			goodsbw.close();
		}
		catch(IOException e){
			System.out.println("予期せぬエラーが発生しました");
			return;
		}
		finally{
			if(storebw != null) storebw.close();
		}
	}
	public static String add(String beforevalue, long additionalvalue){
		String after = null;
		long temp = 0, longkeytemp = 0;

		temp = Long.parseLong(beforevalue);
		longkeytemp = additionalvalue + temp;
		after = String.valueOf(longkeytemp);

		return after;
	}
	public static BufferedReader readFile(String FileName){
		FileReader fr = null;
		try {
			fr = new FileReader(path + File.separator + FileName);
		} catch (FileNotFoundException e) {
			System.out.println("予期せぬエラーが発生しました");
		}
		BufferedReader br = new BufferedReader(fr);
		return br;
	}
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
	public static String putDataToMap(HashMap<String,String> initializationmap, HashMap<String,String> normalmap,
			String[] array, BufferedReader br){
		String str = null;

		initializationmap.put(array[0], "0");
		normalmap.put(array[0], array[1]);

		try {
			str = br.readLine();
		} catch (IOException e) {
			System.out.println("予期せぬエラーが発生しました");
		}
		return str;
	}
	public static void sortEntryMap(List<Map.Entry<String,String>> entries){
        Collections.sort(entries, new Comparator<Map.Entry<String,String>>() {
            @Override
            public int compare(
                  Entry<String,String> entry1, Entry<String,String> entry2) {
            	Long e1 = Long.parseLong(entry2.getValue());
            	Long e2 = Long.parseLong(entry1.getValue());
            	return e1.compareTo(e2);
            }
        });
	}
	public static void writeFile(Entry<String, String> x, int loop, BufferedWriter bw, HashMap<String,String> map){
		try {
			bw.write(x.getKey() + "," + map.get(x.getKey()) + "," + x.getValue());
			if(loop > 1){
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("予期せぬエラーが発生しました");
			return;
		}
	}
}
class Filter implements FilenameFilter{
	public boolean accept(File dir, String name) {
        // 拡張子が"rcd"と一致、かつファイル名が8桁の数字のとき取り出す
		return name.matches("^\\d{8}.rcd$");
	}
}