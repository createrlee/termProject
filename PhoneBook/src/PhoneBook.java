import java.util.ArrayList;

/**
 * �ּҷ� ��ü�� �����մϴ�. 
 * @author createrlee
 *
 */
public class PhoneBook
{
	private int GroupIndex;//Ư�� �׷��� �����ؼ� �۾��� �����Ҷ� �̿�
	private ArrayList<Group> phoneBook;
	
	public PhoneBook()
	{
		ArrayList<Group> phoneBook = new ArrayList<Group>();
		phoneBook.add(new Group());// �̸��� ���� �׷��� �ϳ� �����. �̸��� ���� �׷��� ������ ù��°
		/*�⺻ �׷���� ��� �����Ѵ�. ���߿� �� �߰��Ҽ��� �ִ�.*/
		phoneBook.add(new Group("family"));
		phoneBook.add(new Group("friend"));
		phoneBook.add(new Group("company"));
	}
	
	public void list()
	{
		
	}
	
	public void listGroup()
	{
		
	}
	
	public void add(String name,String phoneNum,String group)
	{
		
	}
	
	public void addGroup(String groupName)
	{
		
	}
	
	public void delete(String name)	
	{
		
	}
	
	/**����ó�� ã�� ������ �����մϴ�
	 * 
	 * @param select :1.�̸� ���� 2.��ȭ��ȣ ����
	 */
	public void change(String name,int select)
	{
		
	}
	
	public void message(String name)
	{
		
	}
	
	public void call(String name)
	{
		
	}
}