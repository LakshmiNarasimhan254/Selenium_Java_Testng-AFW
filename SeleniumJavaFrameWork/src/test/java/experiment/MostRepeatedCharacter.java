package experiment;

public class MostRepeatedCharacter {


	public static void main(String[] args) {
		String strWords = "malayyyallaam";
		MostRepeat(strWords);
		ConsecutiveRepeat(strWords);
	}
		
		public static void MostRepeat(String strWords){
		char[]StrLetters = strWords.toCharArray();
		int ArrayLength = StrLetters.length;
		int count = 1;
		int max =0;
		char chara = 0;
		for (int i =0;i<ArrayLength; i++){
			for (int j =0;j<ArrayLength; j++){
				if (i==j){
					continue;
				}
				if (StrLetters[i]==StrLetters[j]){
						count++;
						if (count>=max){
							max = count;
							chara = StrLetters[i];
						}
					}				
			}
			count = 1;
		}
		System.out.println(max+ " " +chara);

	}
	public static void ConsecutiveRepeat(String strWords){
		int count = 1;
		int max = 0; 
		char maxChar = 0;
		char[]StrLetters = strWords.toCharArray();
		int ArrayLength = StrLetters.length;

		for (int i =1;i<ArrayLength; i++){
			if (StrLetters[i]==StrLetters[i-1]){
				count++;
				if (count>=max){
				max = count;
				maxChar =StrLetters[i-1];
				}
			}else{	

				count = 1 ;
			}		}
		System.out.println(max+ " " +maxChar);
	}}
