/**
 * �� ����� ������ �����մϴ�
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
	public void changeName()
	{
	}
	
	/**�޼����� ��ȯ�մϴ�. ù��° ��Ҵ� ���� �޼���, �ι�° ��Ҵ� �߽� �޼���*/
	public String[] message()
	{
		return this.message;
	}
	
	/**��ȭ��ȣ�� �����մϴ�*/
	public void changeNum()
	{
	}
}