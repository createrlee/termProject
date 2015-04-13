package oodp;

import java.util.ArrayList;

/**
 * Java �ܼ� ȯ�濡�� ���ư��� ���� ���α׷��� ����� ���� �ʿ��� �⺻ ��ҵ��� ĸ��ȭ�մϴ�.<br>
 * <br>
 * <b>���� ä���ڲ� �帮�� ��� ����:</b><br>
 * �� Ŭ������ 2015�⵵ ��ü���⼳������α׷��� �� ������Ʈ#2�� �����Ǿ�����
 * ��� <b>��� ��������</b>�� �� Ŭ���� �� ���ӵ� �ٸ� Ŭ�������� <b>��� ��ɵ�</b>�� ����Ͽ� �� ���� ���� ���α׷��� ����� �����Ͽ����ϴ�.
 * ����, �� Ŭ������ ��� �ǽ������� ����ȭ�� ���ӿ��� �ұ��ϰ�
 * ���� ������ �л��� ��� ���� ���� �ۼ��� �� <b>�ڽ��� �ڵ�</b>�� <b>�ִ� �״��</b> ���̺귯��ȭ�� �Ϳ� ���ϹǷ�,
 * ���� �л���(��� ��������)�� �ٸ� ���� �׸���/�Ǵ� �ٸ� �������� �� Ŭ������ import�Ͽ� ����ϴ� ��쿡��
 * �� Ŭ���� �� ���ӵ� �ٸ� Ŭ������ �ڵ� ������ ���缺 �˻� ���� ������ copy �г�Ƽ�� ������� �ۿ����� �ʵ��� ����� �ֽñ� �ٶ��ϴ�.
 * 
 * @author Racin
 *
 */
public class ConsoleApplication
{
	/**
	 * �ܼ� �Է��� ó���ϱ� ���� ģ���Դϴ�.
	 * �� �ʵ�� private�̹Ƿ� Ŭ���� �ܺο��� ������ �ʽ��ϴ�.
	 */
	private Parser parser;

	/**
	 * �� state ��ȣ�� ���� ����Դϴ�.
	 * �ڼ��� ������ <code>ApplicationStateDefinition.java</code>�� �����ϼ���.<br>
	 * <br>
	 * Note: �빮�ڷ� <code>ASD</code> ġ�� Ctrl + Space�� ������ �ظ��� ��� <code>ApplicationStateDefinition</code>�� �� ��ϴ�.
	 */
	public ArrayList<ApplicationStateDefinition> stateDefinitions;

	/**
	 * ���α׷��� ���� ���¸� ��Ÿ���� ���� ���Դϴ�.
	 * �������� Ư�� state���� Ư�� ��ɾ �Է¹޾��� �� �� �ʵ��� ���� �ٲٵ��� �ڵ带 ���������ν�
	 * ��ü ���α׷��� ���� �帧�� �����ϰ� �˴ϴ�.
	 * 
	 * �ʱⰪ�� 0�Դϴ�.
	 */
	public int state;

	/**
	 * �� ó�� ���� ���α׷��� �ɷ� �ִ� state�Դϴ�.
	 * �� state�� ��� �ƹ� ������ ������ �ʽ��ϴ�.
	 * �Դٰ� �� �ʵ�� private�̹Ƿ� Ŭ���� �ܺο��� ������ �ʽ��ϴ�.
	 */
	private ApplicationStateDefinition nullState;

	public ConsoleApplication()
	{
		parser = new Parser(System.in);
		stateDefinitions = new ArrayList<ApplicationStateDefinition>();
		state = 0;
		nullState = new ApplicationStateDefinition(-1);
	}

	/**
	 * Java �ܼ� �������α׷��� �����մϴ�.<br>
	 * ���ǵ��� ���� state�� �����ϴ� ��� ���� �޽����� ����ϰ� ��� �����մϴ�.<br>
	 * <br>
	 * <b>���� 1: </b>
	 * �� �޼��带 ȣ���ϱ� ���� �ʿ��� ��� ����(�� state ����)�� �Է��� �ξ�� �մϴ�!
	 * state ���Ǹ� �� ���� ��쿣, ���� �׳� ���� ���� �� �Ӹ� �� �� ġ�� �߰��ϸ� �˴ϴ�.<br>
	 * <br>
	 * <b>���� 2: </b>
	 * �� �޼��带 ȣ���ϰ� �� �������� ������ state ���� ��ü�� �� �ǵ帮�� ������.
	 * ���� ���� ���� ������ �ٲٴ� �ڵ�� ��ǻ�� 3�� ��� �� �ϳ��� �ش��ϸ�,
	 * �������� �Ǽ��ڵ带 ����� ���� �� �ƴ� �̻� �׷� �õ��� �����ؾ߸� �մϴ�.
	 */
	public void Start()
	{
		// ��ϵ� state�� ���� ��� ��� ����
		if ( stateDefinitions.isEmpty() == true )
		{
			System.err.printf("Error. state�� �ϳ��� ���ǵǾ� ���� �ʽ��ϴ�. ��� �����մϴ�.\n", state);
			System.exit(-1);
		}

		// previousDef���� '������ ������ state ���ǰ� ��� ����
		ApplicationStateDefinition previousDef = nullState;

		// currentDef���� '�̹��� ������ state ���Ǹ� ã�� ��� �����
		ApplicationStateDefinition currentDef = null;


		while ( true )
		{
			/* -------------------------------------------------------------------------------------------
			 * Phase 1. �̹��� ������ state ���� Ž��
			 */
			currentDef = null;

			// state ���� ��Ͽ��� ���� state���� ID�� ���� ����
			for ( ApplicationStateDefinition def : stateDefinitions )
			{
				if ( def.ID == state )
				{
					currentDef = def;
					break;
				}
			}

			// Ž���� ������ ��� ���� �޽����� ����ϰ� ��� ����
			if ( currentDef == null )
			{
				System.err.printf("Error. ���ǵ��� ���� state(%d)�� ���̸� �õ��߽��ϴ�. ��� �����մϴ�.\n", state);
				System.exit(-1);
			}


			/* -------------------------------------------------------------------------------------------
			 * Phase 2. ���� ���� ó���� ��ƾ ����
			 */
			// ���� state ���ǿ� �̹� state ���ǰ� �ٸ� ��� '���� state�� ���� �� ������ ��ƾ'�� '�̹� state�� ������ �� ������ ��ƾ'�� ����
			if ( previousDef != currentDef && previousDef != null )
			{
				if ( previousDef.callback_Leaving != null )
					previousDef.callback_Leaving.accept(previousDef.ID, StateCallbackType.Leaving);

				if ( currentDef.callback_Entering != null )
					currentDef.callback_Entering.accept(currentDef.ID, StateCallbackType.Entering);
			}
			// �׷��� ���� ��� '�̹� state�� �ݺ��� �� ������ ��ƾ'�� ����
			else
			{
				if ( currentDef.callback_Repeating != null )
					currentDef.callback_Repeating.accept(currentDef.ID, StateCallbackType.Repeating);
			}


			/* -------------------------------------------------------------------------------------------
			 * Phase 3. �޴� �ؽ�Ʈ ���
			 */
			// �� �� �� �� �ٲ�� �޴� �ؽ�Ʈ�� ���� ��� �ݹ� �޼��带 ȣ���Ͽ� �޴� �ؽ�Ʈ ����
			if ( currentDef.callback_UpdateMenuText != null )
				currentDef.menuText = currentDef.callback_UpdateMenuText.apply(currentDef.ID);

			// ���
			System.out.print(currentDef.menuText);


			/* -------------------------------------------------------------------------------------------
			 * Phase 4. state ����
			 */
			// Go-Through state�� �ƴ϶�� ��ɾ� �Է¹ޱ� ����
			if ( currentDef.isGoThroughState == false )
			{
				parser.rules = currentDef.rules;
				parser.Accept();
			}
			// Go-Through state�� �´ٸ� ��� ���� state�� �̵�
			else
			{
				state = currentDef.nextStateToGoThrough;
			}

			/* -------------------------------------------------------------------------------------------
			 * Phase 5. ������ ������ ���� ���� üũ�� ���� �̹� state ���Ǹ� ���� state ���ǰ� �ǵ��� ����
			 */
			previousDef = currentDef;
		}
	}
}
