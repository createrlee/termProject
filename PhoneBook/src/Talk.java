import java.util.ArrayList;
import java.util.Scanner;

/**�ڸ�Ʈ �ϳ��� �װ��� �� �޼������� ��� �޼��������� �˷��ִ� boolean ������ �Բ� ���� �����մϴ�*/
class Comment
{
	String comment;
	boolean isMine;//�� �޼����̸� true, �ƴϸ� false
	
	public Comment(String comment, boolean isMine)
	{
		this.comment = comment;
		this.isMine = isMine;
	}
}

/**
 * ��ű���� ��� �����մϴ�.
 * �޼����� ��ȭ ����� �����մϴ�.
 * @author createrlee
 */
class Talk
{
	private ArrayList<Comment> messageList;
	private Comment tempCom;
	
	public Talk()
	{
		messageList = new ArrayList<Comment>();
		Comment tempCom;
		
		tempCom = new Comment("�ȳ�", false);
		messageList.add(tempCom);
		tempCom = new Comment("�ȳ� �� �� ����?", true);
		messageList.add(tempCom);
		tempCom = new Comment("�� �� ������", false);
		messageList.add(tempCom);
		tempCom = new Comment("�� �� �������µ�...", true);
		messageList.add(tempCom);
		tempCom = new Comment("�� �׷�����", false);
		messageList.add(tempCom);
		tempCom = new Comment("�ȳ�", false);
		messageList.add(tempCom);
	}
	
	/**�޼��� ��ȭâ�� ����ݴϴ�*/
	public void message()
	{
		String input = "";
		Scanner sc=new Scanner(System.in);
		while (!input.equals("exit"))
		{
			System.out.println("*********************************");
			
			for (Comment iComment : messageList)
			{
				if (!iComment.isMine)//������ �޼����� ���
					System.out.format("*%s\n", iComment.comment);
				else//�� �޼����� ���
					System.out.format("%29s*\n", iComment.comment);
			}
			
			System.out.println("*********************************");
			System.out.print("�޼����Է�->");
			input=sc.nextLine();
			this.tempCom=new Comment(input,true);
			messageList.add(this.tempCom);
		}
		
	}
}