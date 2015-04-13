import java.util.ArrayList;

/**
 * 주소록 전체를 구현합니다. 
 * @author createrlee
 *
 */
public class PhoneBook
{
	private int GroupIndex;//특정 그룹을 선택해서 작업을 수행할때 이용
	private ArrayList<Group> phoneBook;
	
	public PhoneBook()
	{
		ArrayList<Group> phoneBook = new ArrayList<Group>();
		phoneBook.add(new Group());// 이름이 없는 그룹을 하나 만든다. 이름이 없는 그룹은 언제나 첫번째
		/*기본 그룹들을 몇가지 정의한다. 나중에 더 추가할수도 있다.*/
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
	
	/**연락처를 찾아 정보를 변경합니다
	 * 
	 * @param select :1.이름 변경 2.전화번호 변경
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