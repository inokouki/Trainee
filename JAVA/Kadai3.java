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
	private static BufferedReader storebr, goodsbr, calcbr, earnbr;
	private static BufferedWriter storebw, goodsbw;
	public static HashMap<String, Long> storecodemap = new HashMap<String, Long>();
	public static HashMap<String, Long> goodscodemap = new HashMap<String, Long>();
	public static HashMap<String, String> goodsmap = new HashMap<String, String>();
	public static HashMap<String, String> storemap = new HashMap<String, String>();
	public static String path = null;

	public static void main (String[] args) throws Exception{
		try{
			String storearray[] = null;
			File dir;

			if (args.length == 1){
				dir = new File(args[0]);
				path = args[0];
			} else {
				System.out.println("予期せぬエラーが発生しました");
				return;
			}

			if (!dir.isDirectory()){
				System.out.println("予期せぬエラーが発生しました");
				return;
			}

			storebr = readDefinitionFile("branch.lst", "支店");
			if (storebr == null) return;
			String storestr = storebr.readLine();

			while(storestr != null){
				storearray = storestr.split("," , 0);

				if (!storearray[0].matches("\\d{3}") || storearray.length != 2){
					System.out.println("支店定義ファイルのフォーマットが不正です");
					return;
				}
				storestr = putDataToMap(storecodemap, storemap, storearray, storebr);
			}
		}
		catch (FileNotFoundException e){
			System.out.println("支店定義ファイルが存在しません");
			return;
		}
		catch (ArrayIndexOutOfBoundsException e){
			System.out.println("支店定義ファイルのフォーマットが不正です");
			return;
		}
		catch (IOException e){
			System.out.println("予期せぬエラーが発生しました");
			return;
		}
		catch (NullPointerException e){
			System.out.println("支店定義ファイルが存在しません");
		}
		finally{
			if(storebr != null) storebr.close();
		}

		try{
			String goodsarray[] = null;

			goodsbr = readDefinitionFile("commodity.lst", "商品");
			if (goodsbr == null) return;
			String goodsstr = goodsbr.readLine();

			while (goodsstr != null){
				goodsarray = goodsstr.split("," , 0);

				if (!goodsarray[0].matches("[a-zA-Z0-9]{8}") || goodsarray.length != 2){
					System.out.println("商品定義ファイルのフォーマットが不正です");
					return;
				}
				goodsstr = putDataToMap(goodscodemap, goodsmap, goodsarray, goodsbr);
			}
		}
		catch (FileNotFoundException e){
			System.out.println("商品定義ファイルが存在しません");
			return;
		}
		catch (ArrayIndexOutOfBoundsException e){
			System.out.println("商品定義ファイルのフォーマットが不正です");
			return;
		}
		catch (IOException e){
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
			String checkvalue=null;

			File calcfile = new File(args[0]);
			File[] filearray = calcfile.listFiles();
			String[]files = calcfile.list(new Filter());

			for (int i=0; i<files.length; i++){
				ArrayList<String> earn = new ArrayList<String>();
				int icount = 0;
				String line, storekey=null, goodskey=null, earnline=null;
				Long before=null, strkeytemp=null;

				tempstr = files[i].split("\\.", 2);
				sucnum.add(tempstr[0]);
				filenametemp = Integer.parseInt(sucnum.get(i));

				if (filenametemp != i+1 || filearray[i].isDirectory()){
					System.out.println("売上ファイル名が連番になっていません");
					return;
				}

				earnbr = readFile(files[i]);
				while ((earnline = earnbr.readLine()) != null){
					earn.add(earnline);
				}
				if (earn.size() != 3){
					System.out.println(files[i] + "のフォーマットが不正です");
					return;
				}

				calcbr = readFile(files[i]);
				while ((line = calcbr.readLine()) != null){
					if (icount > 3){
						System.out.println(files[i] + "のフォーマットが不正です");
						return;
					}
					if (icount == 0){
						storekey = line;

						if (!storecodemap.containsKey(storekey)){
							System.out.println(files[i] + "の支店コードが不正です");
							return;
						}
					}
					if (icount == 1){
						goodskey = line;

						if (!goodscodemap.containsKey(goodskey)){
							System.out.println(files[i] + "の商品コードが不正です");
							return;
						}
					}
					if (icount == 2){
						valuetemp = Long.parseLong(line);

						if (storecodemap.containsKey(storekey)){
							before = storecodemap.get(storekey);
							strkeytemp = add(before, valuetemp);
							checkvalue = String.valueOf(strkeytemp);

							if (checkvalue.matches("^\\d{11,}")){
								System.out.println("合計金額が10桁を超えました");
								return;
							}
							storecodemap.put(storekey, strkeytemp);
						} else {
							System.out.println(files[i] + "の支店コードが不正です");
							return;
						}
						if (goodscodemap.containsKey(goodskey)){
							before = goodscodemap.get(goodskey);
							strkeytemp = add(before, valuetemp);
							checkvalue = String.valueOf(strkeytemp);

							if (checkvalue.matches("^\\d{11,}")){
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
				if (icount != 3){
						System.out.println(files[i] + "のフォーマットが不正です");
						return;
				}
			}
		}
		catch (IOException e){
			System.out.println("予期せぬエラーが発生しました");
		}
		finally{
			if(calcbr != null) calcbr.close();
			if(earnbr != null) earnbr.close();
		}

		try{
			List<Map.Entry<String,Long>> storeentries =
					new ArrayList<Map.Entry<String,Long>>(storecodemap.entrySet());
			List<Map.Entry<String,Long>> goodsentries =
					new ArrayList<Map.Entry<String,Long>>(goodscodemap.entrySet());

			storebw = readyWriteFile("branch.out");
			if (storebw == null){
				System.out.println("予期せぬエラーが発生しました");
				return;
			}
			goodsbw = readyWriteFile("commodity.out");
			if (goodsbw == null){
				System.out.println("予期せぬエラーが発生しました");
				return;
			}

			sortEntryMap(storeentries);
			sortEntryMap(goodsentries);

			for (Entry<String, Long> s : storeentries) {
				writeFile(s, storebw, storemap);
		    }
			for (Entry<String, Long> t : goodsentries) {
				writeFile(t, goodsbw, goodsmap);
		    }
		}
		catch (NullPointerException e){
			System.out.println("予期せぬエラーが発生しました");
			return;
		}
		finally{
			if(storebw != null) storebw.close();
			if(goodsbw != null) goodsbw.close();
		}
	}
	public static Long add(long beforevalue, long additionalvalue){
		return (additionalvalue + beforevalue);
	}
	public static BufferedReader readDefinitionFile(String FileName, String Name){
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(path + File.separator + FileName);
		} catch (FileNotFoundException e) {
			System.out.println(Name + "定義ファイルが存在しません");
			return br = null;
		}

		try{
			br = new BufferedReader(fr);
		}
		catch (NullPointerException e){
			System.out.println(Name + "定義ファイルが存在しません");
			return br = null;
		}
		return br;
	}

	public static BufferedReader readFile(String FileName){
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(path + File.separator + FileName);
		} catch (FileNotFoundException e) {
			System.out.println("予期せぬエラーが発生しました");
		}
		try {
			br = new BufferedReader(fr);
		}
		catch (NullPointerException e){
			System.out.println("予期せぬエラーが発生しました");
		}
		return br;
	}

	public static BufferedWriter readyWriteFile(String FileName){
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(path + File.separator + FileName);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			return bw = null;
		}
		return bw;
	}
	public static String putDataToMap(HashMap<String,Long> initializationmap, HashMap<String,String> normalmap,
			String[] array, BufferedReader br){
		String str = null;

		initializationmap.put(array[0], (long) 0);
		normalmap.put(array[0], array[1]);

		try {
			str = br.readLine();
		} catch (IOException e) {
			return str = null;
		}
		return str;
	}
	public static void sortEntryMap(List<Map.Entry<String,Long>> entries){
        Collections.sort(entries, new Comparator<Map.Entry<String,Long>>() {
            @Override
            public int compare(
                  Entry<String,Long> entry1, Entry<String,Long> entry2) {
            	Long e1 = entry2.getValue();
            	Long e2 = entry1.getValue();
            	return e1.compareTo(e2);
            }
        });
	}
	public static void writeFile(Entry<String, Long> entrymap, BufferedWriter bw, HashMap<String,String> map){
		try {
			bw.write(entrymap.getKey() + "," + map.get(entrymap.getKey()) + "," + entrymap.getValue());
			bw.newLine();
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