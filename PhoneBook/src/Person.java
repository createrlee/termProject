/**
 * 한 사람의 정보를 저장하도록 하는 클래스입니다.
 * @author createrlee
 */
class Person
{
	private String name;
	private String phoneNum;
	private Talk talk;
	
	public Person(String name,String phoneNum)
	{
		this.name=name;
		this.phoneNum=phoneNum;
		talk=new Talk();
	}
	
	/**문자열 2개의 sorting 우선순위 비교.<br>a가 크거나 같으면 false, b가 크면 true.<br>
	 * 정렬하며 저장하려고 하면, 삽입하려는 요소를 a에 넣고 true가 나오는 순간 그자리에 삽입해버리면 됩니다.
	 */
	public static boolean strCmp(String a,String b)
	{
		int cmpSize=(a.length()<b.length() ? a.length():b.length());//둘중 길이가 짧은 것을 기준으로 비교
		
		for(int i=0;i<cmpSize;i++)
		{
			if(a.charAt(i)>b.charAt(i))
				return false;
			else if(a.charAt(i)<b.charAt(i))
				return true;
		}
		return false;
	}
	
	/**이름을 반환합니다*/
	public String name()
	{
		return this.name;
	}
	
	/**전화번호를 반환합니다*/
	public String phoneNum()
	{
		return this.phoneNum;
	}
	
	/**이름을 변경합니다*/
	public void changeName(String name)
	{
		this.name=name;
	}
	
	/**이 사람과의 메세지 창을 띄웁니다*/
	public void message()
	{
		talk.message();
	}
	
	/**이 사람과의 통화기록을 표시합니다*/
	public void call()
	{
		talk.call();
	}
	
	/**전화번호를 변경합니다*/
	public void changeNum(String phoneNum)
	{
		this.phoneNum=phoneNum;
	}
}