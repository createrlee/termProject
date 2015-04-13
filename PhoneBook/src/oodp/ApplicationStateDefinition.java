package oodp;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * ���� ���α׷��� state �ϳ��� ���� ������ �����մϴ�.
 * �� Ŭ������ ConsoleApplication class�� �Բ� ���˴ϴ�.
 * 
 * @author Racin
 *
 */
public class ApplicationStateDefinition
{
	/**
	 * �� state�� �Ϸ� ��ȣ�Դϴ�.
	 * ID�� state���� �����ϰ� �ο��� �ǹ��� �����п��� �ֽ��ϴ�.
	 * ID�� 0�� state�� ���� ���α׷��� ���� state�� �˴ϴ�.
	 */
	public int ID;
	
	/**
	 * �� state�� ����� �Է� ���� �� �� ���� ����Ǵ� state������ ��Ÿ���ϴ�.
	 * �� ���� true�� �� state�� Parser�� ���� ����� �Է� ó�� ���� ����
	 * ���� state�� <code>nextStateToGoThrough</code> �ʵ��� ������ �����մϴ�.<br>
	 * <br>
	 * Note: <code>callback_Entering</code> �� <code>callback_Looping</code>�� ����
	 * Ư�� Ƚ��, Ư�� ���Ǹ�ŭ ������ �ݺ��ϴ� state�� ���� ���� �ֽ��ϴ�.
	 * ����� ������ ���� ������!
	 */
	public boolean isGoThroughState;
	
	/**
	 * <code>isGoThroughState</code> �ʵ��� ���� true�� �� ���Ǵ�
	 * '���� �� ������ ���� state' ��ȣ�Դϴ�.<br>
	 * <br>
	 * <b>����:</b> Ư���� ó�� ��ƾ ���� �� ���� '�� state ��ȣ'�� �����ϸ�
	 * �翬�� ���� ������ ���� �˴ϴ�!
	 */
	public int nextStateToGoThrough;
	
	/**
	 * <code>isGoThroughState</code> �ʵ��� ���� false�� �� ���Ǵ�
	 * '�� state������ ��ɾ� ó�� ��Ģ ���'�Դϴ�.
	 * ������ ���� �� �� ����� �� �� <code>Rule</code> �½��ϴ�.
	 * �׷��� �׳� ���� �˴ϴ�.
	 */
	public ArrayList<Rule> rules;
	
	/**
	 * '����' �޴� ���ڿ��Դϴ�.
	 * <code>callback_UpdateMenuText</code>�� �����Ͽ� '����' �޴� ���ڿ��� ���� ���
	 * �� �ʵ��� ���� ���� �������� �ش� �ݹ� �޼����� ��ȯ���� ��� ä�����ϴ�.
	 */
	public String menuText;
	
	/**
	 * ���� ��Ȳ�� �°� �޴� ���ڿ��� �ٸ��� ǥ���ϰ� ���� �� ����ϴ� �޼��� �ʵ��Դϴ�.
	 * �� �ʵ忡�� 'int �ϳ��� �޾� string�� ��� �޼���'�� �־� �ָ� �˴ϴ�.
	 * ���⼭ �����п��� ���� �� 'int �ϳ�'�� ���� �������� state ��ȣ�Դϴ�.<br>
	 * <br>
	 * Note: ����, argument�� �޴� state ��ȣ�� switch���� �����鼭
	 * ���� state�� ���� ���� �ٸ� �޴� ���ڿ��� ��ȯ�ϴ� �ϳ��� �޼��带 ����� �� ���� �ֽ��ϴ�.
	 * �Ƹ� ��� ���� �л����� �׷� �ڵ� ��Ÿ���� �������״�, �̸� �غ��� �ξ����ϴ�.
	 */
	public Function<Integer, String> callback_UpdateMenuText;
	
	/**
	 * �� state�� ó�� ������ �� ȣ���� �޼��� �ʵ��Դϴ�.
	 * �� �ʵ忡�� 'int �ϳ��� SCT ������ �ϳ��� �ް� ��ȯ�� �� �ϴ� �޼���'�� �־� �ָ� �˴ϴ�.
	 * ���⼭ �����п��� ���� �� 'int �ϳ�'�� ���� �������� state ��ȣ��,
	 * 'SCT ������ �ϳ�'�� �ش� �޼��尡 � �������� ȣ��Ǿ������� ��Ÿ���ϴ�.<br>
	 * <br>
	 * Note: ����, argument�� �޴� ������ switch���� �����鼭
	 * ���� state�� ���� ������ ó���ϴ� �ϳ��� �޼��带 ����� �� ���� �ֽ��ϴ�.
	 * �Ƹ� ��� ���� �л����� �׷� �ڵ� ��Ÿ���� �������״�, �̸� �غ��� �ξ����ϴ�.
	 */
	public BiConsumer<Integer, StateCallbackType> callback_Entering;
	
	/**
	 * �� state�� �ݺ�(ó�� ����) ������ �� ȣ���� �޼��� �ʵ��Դϴ�.
	 * �� �ʵ忡�� 'int �ϳ��� SCT ������ �ϳ��� �ް� ��ȯ�� �� �ϴ� �޼���'�� �־� �ָ� �˴ϴ�.
	 * ���⼭ �����п��� ���� �� 'int �ϳ�'�� ���� �������� state ��ȣ��,
	 * 'SCT ������ �ϳ�'�� �ش� �޼��尡 � �������� ȣ��Ǿ������� ��Ÿ���ϴ�.<br>
	 * <br>
	 * Note: ����, argument�� �޴� ������ switch���� �����鼭
	 * ���� state�� ���� ������ ó���ϴ� �ϳ��� �޼��带 ����� �� ���� �ֽ��ϴ�.
	 * �Ƹ� ��� ���� �л����� �׷� �ڵ� ��Ÿ���� �������״�, �̸� �غ��� �ξ����ϴ�.
	 */
	public BiConsumer<Integer, StateCallbackType> callback_Repeating;
	
	/**
	 * �� state�� ���� �� ȣ���� �޼��� �ʵ��Դϴ�.
	 * �� �ʵ忡�� 'int �ϳ��� SCT ������ �ϳ��� �ް� ��ȯ�� �� �ϴ� �޼���'�� �־� �ָ� �˴ϴ�.
	 * ���⼭ �����п��� ���� �� 'int �ϳ�'�� ���� �������� state ��ȣ��,
	 * 'SCT ������ �ϳ�'�� �ش� �޼��尡 � �������� ȣ��Ǿ������� ��Ÿ���ϴ�.<br>
	 * <br>
	 * Note: ����, argument�� �޴� ������ switch���� �����鼭
	 * ���� state�� ���� ������ ó���ϴ� �ϳ��� �޼��带 ����� �� ���� �ֽ��ϴ�.
	 * �Ƹ� ��� ���� �л����� �׷� �ڵ� ��Ÿ���� �������״�, �̸� �غ��� �ξ����ϴ�.
	 */
	public BiConsumer<Integer, StateCallbackType> callback_Leaving;
	
	/**
	 * ���ο� <code>ApplicationStateDefinition class</code>�� �ν��Ͻ��� �ʱ�ȭ�մϴ�.
	 * �� �����ڸ� ����ϴ� ��� <code>isGoThroughState</code> �ʵ��� ���� �ڵ����� false�� �ʱ�ȭ�˴ϴ�.
	 * 
	 * @param ID �� state�� �Ϸ� ��ȣ�Դϴ�.
	 */
	public ApplicationStateDefinition(int ID)
	{
		this.ID = ID;
		isGoThroughState = false;
		rules = new ArrayList<Rule>();
		menuText = String.format("State#%d�� ���� �⺻ �޴� �ؽ�Ʈ�Դϴ�.", ID);
		callback_UpdateMenuText = null;
		callback_Entering = null;
		callback_Repeating = null;
		callback_Leaving = null;
	}
	
	/**
	 * ���ο� <code>ApplicationStateDefinition class</code>�� �ν��Ͻ��� �ʱ�ȭ�մϴ�.
	 * �� �����ڸ� ����ϴ� ��� <code>isGoThroughState</code> �ʵ��� ���� �ڵ����� <b>true</b>�� �ʱ�ȭ�˴ϴ�.
	 * 
	 * @param ID �� state�� �Ϸ� ��ȣ�Դϴ�.
	 * @param nextStateToGoThrough �� state�� ������ ������ ������ ���� state ��ȣ�Դϴ�.
	 */
	public ApplicationStateDefinition(int ID, int nextStateToGoThrough)
	{
		this.ID = ID;
		this.isGoThroughState = true;
		this.nextStateToGoThrough = nextStateToGoThrough;
		rules = new ArrayList<Rule>();
		menuText = String.format("State#%d�� ���� �⺻ �޴� �ؽ�Ʈ�Դϴ�.", ID);
		callback_UpdateMenuText = null;
		callback_Entering = null;
		callback_Leaving = null;
	}
}
