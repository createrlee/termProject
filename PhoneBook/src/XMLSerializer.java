

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 * �־��� XML ������ Ȱ���Ͽ� �ν��Ͻ� <b>�ϳ�</b>�� ����ϰų� �о� ���� �۾��� ĸ��ȭ�մϴ�.<br>
 * <br>
 * <b>���� ä���ڲ� �帮�� ��� ����:</b><br>
 * �� Ŭ������ 2015�⵵ ��ü���⼳������α׷��� 4���� �ǽ� ������ ���Ǿ�����
 * ��� <b>��� ��������</b>�� �� Ŭ���� �� ���ӵ� �ٸ� Ŭ�������� <b>��� ��ɵ�</b>�� ���������� ������ ���ҽ��ϴ�.
 * ����, �� Ŭ������ ��� �ǽ������� �ۼ��� �����ӿ��� �ұ��ϰ�
 * ���� ������ �л��� ��� ���� ���� �ۼ��� �� <b>�ڽ��� �ڵ�</b>�� <b>�ִ� �״��</b> ���̺귯��ȭ�� �Ϳ� ���ϹǷ�,
 * ���� �л���(��� ��������)�� �ٸ� ���� �׸���/�Ǵ� �ٸ� �������� �� Ŭ������ import�Ͽ� ����ϴ� ��쿡��
 * �� Ŭ���� �� ���ӵ� �ٸ� Ŭ������ �ڵ� ������ ���缺 �˻� ���� ������ copy �г�Ƽ�� ������� �ۿ����� �ʵ��� ����� �ֽñ� �ٶ��ϴ�.
 * 
 * @param <ElementType>
 *            ����ϰų� �о� �� �����Դϴ�.
 * 
 * @author Racin
 *
 */
public class XMLSerializer<T>
{
	/**
	 * JAXB�� ������ �𸣴� ����̴� �׳� �׷����� �ϰ� �Ѿ�ô�.
	 * �� �ʵ�� private�̹Ƿ� �������� �� �� �����ϴ�.
	 */
	private JAXBContext context;

	/**
	 * Class<T> class�� T class�� class�� ��� class�Դϴ�.
	 * �ٽ� ���ϸ�,
	 * � Ŭ���� ���Ŀ� ����, �� Ŭ������ �� �ʵ带 ��� �ִ���, �̸��� ����, �޼���� ���� �ִ��� ���
	 * �츮�� .java ���Ͽ��� ���� �� �� ������� ��� ���� �����ϴ� ģ���Դϴ�.
	 * �� ģ���� ���� �ǽ� �ð��� �� �� �� ����ϸ鼭 ���� ��� �����̴� ������ �׳� �׷����� �ϰ� �Ѿ�ô�.
	 * �� �ʵ�� private�̹Ƿ� �������� �� �� �����ϴ�.
	 */
	private Class<T> classType;

	/**
	 * ���ο� XMLSerializer class�� �ν��Ͻ��� �ʱ�ȭ�մϴ�.
	 * 
	 * @param classType
	 *            Java�� ������ �Ѱ� ������ ������� ���� �μ��Դϴ�.
	 *            ���⿡��, �������� �� Ŭ������ ������ <code>XMLSerializer&ltPerson&gt</code> ...�� ���� �������� ��, <code>Person.class</code> ��� ���� ������ �˴ϴ�.
	 *            �̰� ������ ��쿡��... Person���� �ʵ忡 ���� ��� <code>getClass()</code>�� ȣ���� ���� ������ ������� <code>Class<Person></code>�������� ĳ������ �־� �ָ� �˴ϴ�.
	 *            �� ���� ������ �ƴ� ��� ���µ�... Ctrl + 1 ������ ���� �� �׸�(��� ��������� ����)�� �����Ͽ� ��� ���� ���� �ּ���.
	 *            �������� ���ݸ� �����ô�.
	 * 
	 * @throws JAXBException
	 *             JAXB(������ �𸣴� ���)���� ������ ����� �� �߻��ϴ� �����Դϴ�.
	 *             ���� xml ������ �ش� Ŭ������ ȣȯ���� ���� �� �߻��մϴ�.
	 */
	public XMLSerializer(Class<T> classType) throws JAXBException
	{
		context = JAXBContext.newInstance(classType);
		this.classType = classType;
	}

	/**
	 * �־��� ���Ͽ� �ش� ��Ҹ� �����մϴ�.
	 * ������ ������ ������ ��ٷ� �����ϴ�.
	 * �� �޼����� ������ ������ google���� ã�� �� ���̴� �����Ϸ� ���� �ʾƵ� �˴ϴ�.
	 * 
	 * @param fileName
	 *            ������ ���� �̸��Դϴ�.
	 * @param elementToSerialize
	 *            ������ ����Դϴ�.
	 * @throws JAXBException
	 *             JAXB(������ �𸣴� ���)���� ������ ����� �� �߻��ϴ� �����Դϴ�.
	 *             ���� xml ������ �ش� Ŭ������ ȣȯ���� ���� �� �߻��մϴ�.
	 * @throws IOException
	 *             ������ ���� ���� ���� ������ ��(�ٸ� ���α׷��� ������� ��, ��� ������ �̻��� ��, �ϵ� �뷮�� �� á�� �� ��) �߻��ϴ� �����Դϴ�.
	 */
	public void Save(String fileName, T elementToSerialize) throws JAXBException, IOException
	{
		FileOutputStream os = new FileOutputStream(fileName);

		Save(os, elementToSerialize);

		os.close();
	}

	/**
	 * �־��� ��Ʈ���� �ش� ��Ҹ� ����մϴ�.
	 * ��Ʈ���� ������ ������ ������ ���� ä�� ���� �ֽ��ϴ�.
	 * �� �޼����� ������ ������ google���� ã�� �� ���̴� �����Ϸ� ���� �ʾƵ� �˴ϴ�.<br>
	 * <br>
	 * <b>Note:</b> os �ڸ��� <code>System.out</code>�� ������ �ܼ� â�� XML ������ ����� ������ Eclipse �ܼ��̶� ���ڵ� ȣȯ�� �� �Ǵ� ��õ���� �ʽ��ϴ�.
	 * 
	 * @param os
	 *            ����� ��� ��Ʈ���Դϴ�.
	 * @param elementToSerialize
	 *            ����� ����Դϴ�.
	 * @throws JAXBException
	 *             JAXB(������ �𸣴� ���)���� ������ ����� �� �߻��ϴ� �����Դϴ�.
	 *             ���� xml ������ �ش� Ŭ������ ȣȯ���� ���� �� �߻��մϴ�.
	 * @throws IOException
	 *             ������ ���� ���� ���� ������ ��(�ٸ� ���α׷��� ������� ��, ��� ������ �̻��� ��, �ϵ� �뷮�� �� á�� �� ��) �߻��ϴ� �����Դϴ�.
	 */
	public void Save(OutputStream os, T elementToSerialize) throws JAXBException
	{
		Marshaller m = context.createMarshaller();

		m.marshal(elementToSerialize, os);
	}

	/**
	 * �־��� ���Ͽ��� ��Ҹ� �о� �� �ν��Ͻ��� ����� ��ȯ�մϴ�.
	 * ������ �о���Ⱑ ������ ��ٷ� �����ϴ�.
	 * �� �޼����� ������ ������ google���� ã�� �� ���̴� �����Ϸ� ���� �ʾƵ� �˴ϴ�.
	 * 
	 * @param fileName
	 *            �о� �� ���� �̸��Դϴ�.
	 * @throws JAXBException
	 *             JAXB(������ �𸣴� ���)���� ������ ����� �� �߻��ϴ� �����Դϴ�.
	 *             ���� xml ������ �ش� Ŭ������ ȣȯ���� ���� �� �߻��մϴ�.
	 * @throws IOException
	 *             ������ �б� ���� ���� ������ ��(�ٸ� ���α׷��� �������� ��, ��� ������ �̻��� ��, �ش� ������ ���� �� ��) �߻��ϴ� �����Դϴ�.
	 */
	public T Load(String fileName) throws IOException, JAXBException
	{
		FileInputStream is = new FileInputStream(fileName);
		T result = Load(is);
		is.close();
		return result;
	}

	/**
	 * �־��� ��Ʈ������ ��Ҹ� �о� �� �ν��Ͻ��� ����� ��ȯ�մϴ�.
	 * ��Ʈ���� �о���Ⱑ ������ ������ ���� ä�� ���� �ֽ��ϴ�.
	 * �� �޼����� ������ ������ google���� ã�� �� ���̴� �����Ϸ� ���� �ʾƵ� �˴ϴ�.<br>
	 * <br>
	 * <b>Note:</b> <code>Save()</code>�� �޸�, �������� �ܼ� â���� XML ������ ���� �Է��ϴ� ���� �ſ� ���뽺���� ���Դϴ�.
	 * ����� �õ����� ������!
	 * 
	 * @param is
	 *            �о� �� ��Ʈ���Դϴ�.
	 * @throws JAXBException
	 *             JAXB(������ �𸣴� ���)���� ������ ����� �� �߻��ϴ� �����Դϴ�.
	 *             ���� xml ������ �ش� Ŭ������ ȣȯ���� ���� �� �߻��մϴ�.
	 * @throws IOException
	 *             ������ �б� ���� ���� ������ ��(�ٸ� ���α׷��� �������� ��, ��� ������ �̻��� ��, �ش� ������ ���� �� ��) �߻��ϴ� �����Դϴ�.
	 */
	public T Load(InputStream is) throws JAXBException
	{
		Unmarshaller u = context.createUnmarshaller();
		JAXBElement<T> result = u.unmarshal(new StreamSource(is), classType);
		return result.getValue();
	}
}
