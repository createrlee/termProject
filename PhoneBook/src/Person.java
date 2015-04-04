/**
 * 한 사람의 정보를 저장합니다
 * @author createrlee
 *
 */
class Person
{
	private String name;
	private String phoneNum;
	private String[] message;
	
	public Person(String name,String phoneNum)
	{
		this.name=name;
		this.phoneNum=phoneNum;
		message=new String[2];
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
	public void changeName()
	{
	}
	
	/**메세지를 반환합니다. 첫번째 요소는 수신 메세지, 두번째 요소는 발신 메세지*/
	public String[] message()
	{
		return this.message;
	}
	
	/**전화번호를 변경합니다*/
	public void changeNum()
	{
	}
}