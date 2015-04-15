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

/**��ȭ����� �ð��� ������� �����ϰ� �߽����� ���������� �˷��ִ� boolean ������ �Բ� ���� �����մϴ�*/
class PhoneCall
{
	String phoneCall;
	boolean isMyCall;//�߽��̸� true, �ƴϸ� false
	
	public PhoneCall(String phoneCall, boolean isMyCall)
	{
		this.phoneCall=phoneCall;
		this.isMyCall=isMyCall;
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
	private ArrayList<PhoneCall> callList;
	
	public Talk()
	{
		messageList = new ArrayList<Comment>();
		callList = new ArrayList<PhoneCall>();
		Comment tempCom;
		PhoneCall tempCall;
		
		tempCom = new Comment("�ȳ�", false);
		messageList.add(tempCom);
		tempCom = new Comment("�ȳ� �� �� ����?", true);
		messageList.add(tempCom);
		tempCom = new Comment("�� �� ������", false);
		messageList.add(tempCom);
		tempCom = new Comment("�� �� �������µ�...", true);
		messageList.add(tempCom);
		tempCom = new Comment("�� �׷�����...", false);
		messageList.add(tempCom);
		tempCom = new Comment("�׷� �ȳ�", false);
		messageList.add(tempCom);
		
		tempCall = new PhoneCall("15.3.18 16:38",false);
		callList.add(tempCall);
		tempCall = new PhoneCall("15.3.21 12:13",true);
		callList.add(tempCall);
		tempCall = new PhoneCall("15.3.30 20:40",true);
		callList.add(tempCall);
		tempCall = new PhoneCall("15.4.1 12:02",false);
		callList.add(tempCall);
		tempCall = new PhoneCall("15.4.6 19:48",true);
		callList.add(tempCall);
		tempCall = new PhoneCall("15.4.15 11:57",false);
		callList.add(tempCall);
	}
	
	/**�޼��� ��ȭâ�� ����ݴϴ�. exit�� ġ�� ��ȭâ���� �����ϴ�.*/
	public void message()
	{
		String input = "";
		Scanner sc=new Scanner(System.in);
		Comment tempCom;//������ �޼����� ������ �ӽ÷� ����
		while (true)
		{
			System.out.println("*********************************");
			
			for (Comment iComment : messageList)
			{
				if (!iComment.isMine)//������ �޼����� ���
					System.out.format("*%s\n", iComment.comment);
				else//�� �޼����� ���
					System.out.format("%29s*\n", iComment.comment);
			}
			
			System.out.println("*********************************  exit�� ġ�� ��ȭâ���� �����ϴ�");
			System.out.print("�޼����Է�->");
			input=sc.nextLine();
			if(input.equals("exit"))
				break;
			tempCom=new Comment(input,true);
			messageList.add(tempCom);
		}	
	}
	
	/**
	 * ��ȭ����� ����մϴ�.
	 */
	public void call()
	{
		System.out.println("*********************************");
		for(PhoneCall iPhoneCall:callList)
		{
			if(iPhoneCall.isMyCall)//�߽���ȭ�� ���
				System.out.println(iPhoneCall.phoneCall+"-> �߽�");
			else//������ȭ�� ���
				System.out.println(iPhoneCall.phoneCall+"<- ����");
			System.out.println("---------------------------------");
		}
		System.out.println("*********************************");
	}
}