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
	
	/**�޼����� ��ȯ�մϴ�. ù��° ��Ҵ� ���� �޼���, �ι�° ��Ҵ� �߽� �޼���*/
	/*public String[] message()
	{
		
	}*/
	
	/**��ȭ��ȣ�� �����մϴ�*/
	public void changeNum(String phoneNum)
	{
		this.phoneNum=phoneNum;
	}
}