package za.co.pokerface.baseCard.enums;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import za.co.pokerface.baseCard.ICardInfo;

/**
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public interface IMapperEnum {
	public Enum<? extends ICardInfo> getReferenceInternal();
	public Enum<?> getReferenceExternal();
	public Exception getException();
	public boolean isMapped(Object... hand);
	public Enum<? extends IMapperEnum> getFromInternalRef(Enum<? extends ICardInfo> internalRef) throws Exception;
	
	/**
	 * 
	 * @param search
	 * @param mapper
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws Exception
	 */
	public static ArrayList<Enum<?>> getExternalFromInternal(Enum<? extends ICardInfo> search, Enum<? extends IMapperEnum> mapper) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, Exception { 
		ArrayList<Enum<?>> tmpList = new ArrayList<Enum<?>>();
		
		for (Enum<? extends IMapperEnum> mapObj : mapper.getDeclaringClass().getEnumConstants() ) { 
			if (( (IMapperEnum) mapObj).getReferenceInternal().equals(search)) tmpList.add( ((IMapperEnum) mapObj).getReferenceExternal() );
		}
		
		if ( tmpList.size() < 1)  throwCustomException(("Mapping not found " + search.name()), mapper);
 		
		return tmpList;
	}
	
	/**
	 * 
	 * @param searchCard
	 * @return
	 * @throws Exception 
	 */
	public static Enum<? extends ICardInfo> getInternalFromExternal(Enum<?> search, Enum<? extends IMapperEnum> mapper)
			throws Exception { 
		for (Enum<? extends IMapperEnum> mapObj : mapper.getDeclaringClass().getEnumConstants()  ) { 
			if ( ( (IMapperEnum) mapObj).getReferenceExternal().equals(search)) return ( (IMapperEnum) mapObj).getReferenceInternal();
		}
		
		throwCustomException(("Mapping not found " + search.name()), mapper);
 
		return null;
	}
	
/**
 * 
 * @param message
 * @param mapper
 * @throws Exception
 * @throws InstantiationException
 * @throws IllegalAccessException
 * @throws InvocationTargetException
 * @throws NoSuchMethodException
 */
	public static void throwCustomException(String message, Enum<? extends IMapperEnum> mapper)
			throws Exception, InstantiationException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Class<? extends Exception> exceptionClass = ((IMapperEnum) mapper).getException().getClass();
		
		Class<?>[] cArg = new Class[1];
        cArg[0] = String.class;
        
        throw ( (Exception) exceptionClass.getDeclaredConstructor(cArg).newInstance(message) );
	}
}