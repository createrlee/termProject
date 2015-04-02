

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
 * 주어진 XML 파일을 활용하여 인스턴스 <b>하나</b>를 기록하거나 읽어 오는 작업을 캡슐화합니다.<br>
 * <br>
 * <b>과제 채점자께 드리는 당부 말씀:</b><br>
 * 이 클래스는 2015년도 객체지향설계및프로그래밍 4주차 실습 예제로 사용되었으며
 * 당시 <b>모든 수강생들</b>이 이 클래스 및 종속된 다른 클래스들의 <b>모든 기능들</b>을 성공적으로 구현해 보았습니다.
 * 따라서, 이 클래스는 당시 실습조교가 작성한 원본임에도 불구하고
 * 세부 내용은 학생들 모두 각자 직접 작성해 본 <b>자신의 코드</b>를 <b>있는 그대로</b> 라이브러리화한 것에 준하므로,
 * 이후 학생들(당시 수강생들)이 다른 수업 그리고/또는 다른 과제에서 이 클래스를 import하여 사용하는 경우에도
 * 이 클래스 및 종속된 다른 클래스의 코드 파일이 유사성 검사 등을 수반한 copy 패널티의 대상으로 작용하지 않도록 배려해 주시기 바랍니다.
 * 
 * @param <ElementType>
 *            기록하거나 읽어 올 형식입니다.
 * 
 * @author Racin
 *
 */
public class XMLSerializer<T>
{
	/**
	 * JAXB는 조교도 모르는 기술이니 그냥 그러려니 하고 넘어갑시다.
	 * 이 필드는 private이므로 여러분이 볼 수 없습니다.
	 */
	private JAXBContext context;

	/**
	 * Class<T> class는 T class의 class를 담는 class입니다.
	 * 다시 말하면,
	 * 어떤 클래스 형식에 대해, 그 클래스가 어떤어떤 필드를 담고 있는지, 이름은 뭔지, 메서드는 뭐뭐 있는지 등등
	 * 우리가 .java 파일에서 적어 둔 그 내용들을 담기 위해 존재하는 친구입니다.
	 * 이 친구는 다음 실습 시간에 몇 번 더 사용하면서 차차 배울 예정이니 오늘은 그냥 그러려니 하고 넘어갑시다.
	 * 이 필드는 private이므로 여러분이 볼 수 없습니다.
	 */
	private Class<T> classType;

	/**
	 * 새로운 XMLSerializer class의 인스턴스를 초기화합니다.
	 * 
	 * @param classType
	 *            Java의 문법적 한계 때문에 어거지로 만든 인수입니다.
	 *            여기에는, 여러분이 이 클래스의 형식을 <code>XMLSerializer&ltPerson&gt</code> ...와 같이 지정했을 때, <code>Person.class</code> 라고 적어 넣으면 됩니다.
	 *            이게 오류날 경우에는... Person형식 필드에 쩜을 찍고 <code>getClass()</code>를 호출한 다음 나오는 결과값을 <code>Class<Person></code>형식으로 캐스팅해 넣어 주면 됩니다.
	 *            이 때는 오류가 아닌 경고가 나는데... Ctrl + 1 누르고 가장 윗 항목(경고를 명시적으로 무시)을 선택하여 노란 줄을 지워 주세요.
	 *            더럽지만 조금만 참읍시다.
	 * 
	 * @throws JAXBException
	 *             JAXB(조교도 모르는 기술)에서 오류를 뱉었을 때 발생하는 예외입니다.
	 *             보통 xml 파일이 해당 클래스와 호환되지 않을 때 발생합니다.
	 */
	public XMLSerializer(Class<T> classType) throws JAXBException
	{
		context = JAXBContext.newInstance(classType);
		this.classType = classType;
	}

	/**
	 * 주어진 파일에 해당 요소를 저장합니다.
	 * 파일은 저장이 끝나면 곧바로 닫힙니다.
	 * 이 메서드의 내용은 조교도 google에서 찾아 온 것이니 이해하려 하지 않아도 됩니다.
	 * 
	 * @param fileName
	 *            저장할 파일 이름입니다.
	 * @param elementToSerialize
	 *            저장할 요소입니다.
	 * @throws JAXBException
	 *             JAXB(조교도 모르는 기술)에서 오류를 뱉었을 때 발생하는 예외입니다.
	 *             보통 xml 파일이 해당 클래스와 호환되지 않을 때 발생합니다.
	 * @throws IOException
	 *             파일을 쓰기 모드로 열지 못했을 때(다른 프로그램이 열어놨을 때, 경로 설정이 이상할 때, 하드 용량이 꽉 찼을 때 등) 발생하는 예외입니다.
	 */
	public void Save(String fileName, T elementToSerialize) throws JAXBException, IOException
	{
		FileOutputStream os = new FileOutputStream(fileName);

		Save(os, elementToSerialize);

		os.close();
	}

	/**
	 * 주어진 스트림에 해당 요소를 기록합니다.
	 * 스트림은 저장이 끝나도 닫히지 않은 채로 남아 있습니다.
	 * 이 메서드의 내용은 조교도 google에서 찾아 온 것이니 이해하려 하지 않아도 됩니다.<br>
	 * <br>
	 * <b>Note:</b> os 자리에 <code>System.out</code>을 넣으면 콘솔 창에 XML 내용을 출력해 주지만 Eclipse 콘솔이랑 인코딩 호환이 안 되니 추천하진 않습니다.
	 * 
	 * @param os
	 *            기록할 출력 스트림입니다.
	 * @param elementToSerialize
	 *            기록할 요소입니다.
	 * @throws JAXBException
	 *             JAXB(조교도 모르는 기술)에서 오류를 뱉었을 때 발생하는 예외입니다.
	 *             보통 xml 파일이 해당 클래스와 호환되지 않을 때 발생합니다.
	 * @throws IOException
	 *             파일을 쓰기 모드로 열지 못했을 때(다른 프로그램이 열어놨을 때, 경로 설정이 이상할 때, 하드 용량이 꽉 찼을 때 등) 발생하는 예외입니다.
	 */
	public void Save(OutputStream os, T elementToSerialize) throws JAXBException
	{
		Marshaller m = context.createMarshaller();

		m.marshal(elementToSerialize, os);
	}

	/**
	 * 주어진 파일에서 요소를 읽어 와 인스턴스를 만들어 반환합니다.
	 * 파일은 읽어오기가 끝나면 곧바로 닫힙니다.
	 * 이 메서드의 내용은 조교도 google에서 찾아 온 것이니 이해하려 하지 않아도 됩니다.
	 * 
	 * @param fileName
	 *            읽어 올 파일 이름입니다.
	 * @throws JAXBException
	 *             JAXB(조교도 모르는 기술)에서 오류를 뱉었을 때 발생하는 예외입니다.
	 *             보통 xml 파일이 해당 클래스와 호환되지 않을 때 발생합니다.
	 * @throws IOException
	 *             파일을 읽기 모드로 열지 못했을 때(다른 프로그램이 독점중일 때, 경로 설정이 이상할 때, 해당 파일이 없을 때 등) 발생하는 예외입니다.
	 */
	public T Load(String fileName) throws IOException, JAXBException
	{
		FileInputStream is = new FileInputStream(fileName);
		T result = Load(is);
		is.close();
		return result;
	}

	/**
	 * 주어진 스트림에서 요소를 읽어 와 인스턴스를 만들어 반환합니다.
	 * 스트림은 읽어오기가 끝나도 닫히지 않은 채로 남아 있습니다.
	 * 이 메서드의 내용은 조교도 google에서 찾아 온 것이니 이해하려 하지 않아도 됩니다.<br>
	 * <br>
	 * <b>Note:</b> <code>Save()</code>와 달리, 여러분이 콘솔 창으로 XML 내용을 직접 입력하는 것은 매우 고통스러운 일입니다.
	 * 저얼대 시도하지 마세요!
	 * 
	 * @param is
	 *            읽어 올 스트림입니다.
	 * @throws JAXBException
	 *             JAXB(조교도 모르는 기술)에서 오류를 뱉었을 때 발생하는 예외입니다.
	 *             보통 xml 파일이 해당 클래스와 호환되지 않을 때 발생합니다.
	 * @throws IOException
	 *             파일을 읽기 모드로 열지 못했을 때(다른 프로그램이 독점중일 때, 경로 설정이 이상할 때, 해당 파일이 없을 때 등) 발생하는 예외입니다.
	 */
	public T Load(InputStream is) throws JAXBException
	{
		Unmarshaller u = context.createUnmarshaller();
		JAXBElement<T> result = u.unmarshal(new StreamSource(is), classType);
		return result.getValue();
	}
}
