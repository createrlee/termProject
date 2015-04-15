/**
 * �� ����� ������ �����ϵ��� �ϴ� Ŭ�����Դϴ�.
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
	
	/**���ڿ� 2���� sorting �켱���� ��.<br>a�� ũ�ų� ������ false, b�� ũ�� true.<br>
	 * �����ϸ� �����Ϸ��� �ϸ�, �����Ϸ��� ��Ҹ� a�� �ְ� true�� ������ ���� ���ڸ��� �����ع����� �˴ϴ�.
	 */
	public static boolean strCmp(String a,String b)
	{
		int cmpSize=(a.length()<b.length() ? a.length():b.length());//���� ���̰� ª�� ���� �������� ��
		
		for(int i=0;i<cmpSize;i++)
		{
			if(a.charAt(i)>b.charAt(i))
				return false;
			else if(a.charAt(i)<b.charAt(i))
				return true;
		}
		return false;
	}
	
	/**�̸��� ��ȯ�մϴ�*/
	public String name()
	{
		return this.name;
	}
	
	/**��ȭ��ȣ�� ��ȯ�մϴ�*/
	public String phoneNum()
	{
		return this.phoneNum;
	}
	
	/**�̸��� �����մϴ�*/
	public void changeName(String name)
	{
		this.name=name;
	}
	
	/**�� ������� �޼��� â�� ���ϴ�*/
	public void message()
	{
		talk.message();
	}
	
	/**�� ������� ��ȭ����� ǥ���մϴ�*/
	public void call()
	{
		talk.call();
	}
	
	/**��ȭ��ȣ�� �����մϴ�*/
	public void changeNum(String phoneNum)
	{
		this.phoneNum=phoneNum;
	}
}