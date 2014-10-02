package jon.sandbox.code.helper;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClassHelper
{
  /**
   * Get the underlying class for a type, or null if the type is a variable type.
   * @param type the type
   * @return the underlying class
   */
  public static Class<?> getClass(Type type)
  {
    if (type instanceof Class) {
      return (Class<?>) type;
    }
    else if (type instanceof ParameterizedType) {
      return getClass(((ParameterizedType) type).getRawType());
    }
    else if (type instanceof GenericArrayType)
    {
      Type componentType = ((GenericArrayType) type).getGenericComponentType();
      Class<?> componentClass = getClass(componentType);
      if (componentClass != null ) {
        return Array.newInstance(componentClass, 0).getClass();
      }
      else {
        return null;
      }
    }
    else {
      return null;
    }
  }

// Note that we basically "give up" if we hit an unbound type variable;
// since the goal here is to find a class, we might as well.
// The next step is a bit more involved. We need to look at the actual type
// arguments provided to the super class of the class in question. If that super
// class is the base class we are interested in, then we are done. Otherwise,
// we need to repeat this process. However, the actual type arguments we have
// just looked at may themselves be used as actual type arguments to the next
// class up the inheritance hierarchy. Unfortunately, Java will not track this
// for us; we'll need to do it ourselves.

  /**
   * Get the actual type arguments a child class has used to extend a generic base class.
   *
   * @param baseClass the base class
   * @param childClass the child class
   * @return a list of the raw classes for the actual type arguments.
   */
  public static <T> List<Class<?>> getTypeArguments(
    Class<T> baseClass, Class<? extends T> childClass)
  {
    Map<Type, Type> resolvedTypes = new HashMap<Type, Type>();
    Type type = childClass;
    // start walking up the inheritance hierarchy until we hit baseClass
    while (! getClass(type).equals(baseClass))
    {
      if (type instanceof Class) {
        // there is no useful information for us in raw types, so just keep going.
        type = ((Class<?>) type).getGenericSuperclass();
      }
      else {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Class<?> rawType = (Class<?>) parameterizedType.getRawType();
  
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
        for (int i = 0; i < actualTypeArguments.length; i++) {
          resolvedTypes.put(typeParameters[i], actualTypeArguments[i]);
        }
  
        if (!rawType.equals(baseClass)) {
          type = rawType.getGenericSuperclass();
        }
      }
    }
  
    // finally, for each actual type argument provided to baseClass, determine (if possible)
    // the raw class for that type argument.
    Type[] actualTypeArguments;
    if (type instanceof Class) {
      actualTypeArguments = ((Class<?>) type).getTypeParameters();
    }
    else {
      actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
    }
    List<Class<?>> typeArgumentsAsClasses = new ArrayList<Class<?>>();
    // resolve types by chasing down type variables.
    for (Type baseType: actualTypeArguments) {
      while (resolvedTypes.containsKey(baseType)) {
        baseType = resolvedTypes.get(baseType);
      }
      typeArgumentsAsClasses.add(getClass(baseType));
    }
    return typeArgumentsAsClasses;
  }

//Finally, we can accomplish our original goal:
//  public abstract class AbstractUserType<T> implements UserType {
//    ...
//    public Class returnedClass {
//      return getTypeArguments(AbstractUserType.class, getClass()).get(0);
//    }
//    ...
//  }

//static public Class<?> findSuperClassParameterType(
//  Object instance, Class<?> classOfInterest, int parameterIndex)
  public static Class<?> findSubClassParameterType(
    Object instance, Class<?> classOfInterest, int parameterIndex)
  {
    Map<Type, Type> typeMap = new HashMap<Type, Type>();
    Class<?> instanceClass = instance.getClass();

    while (classOfInterest != instanceClass.getSuperclass())
    {
      extractTypeArguments(typeMap, instanceClass);
      instanceClass = instanceClass.getSuperclass();
      if (instanceClass == null) throw new IllegalArgumentException();
    }
      
      ParameterizedType parameterizedType = (ParameterizedType) instanceClass.getGenericSuperclass();
      Type actualType = parameterizedType.getActualTypeArguments()[parameterIndex];

      if (typeMap.containsKey(actualType)) {
        actualType = typeMap.get(actualType);
      }
      
      if (actualType instanceof Class) {
        return (Class<?>) actualType;
      } else if (actualType instanceof TypeVariable) {
        return browseNestedTypes(instance, (TypeVariable<?>) actualType);
      } else {
        throw new IllegalArgumentException();
      }
    }
      
  private static Class<?> browseNestedTypes(Object instance, TypeVariable<?> actualType)
  {
    Class<?> instanceClass = instance.getClass();
    List<Class<?>> nestedOuterTypes = new LinkedList<Class<?>>();

    for (
      Class<?> enclosingClass = instanceClass.getEnclosingClass();
      enclosingClass != null;
      enclosingClass = enclosingClass.getEnclosingClass())
    {
      try
      {
        Field this$0 = instanceClass.getDeclaredField("this$0");
        Object outerInstance = this$0.get(instance);
        Class<?> outerClass = outerInstance.getClass();
        nestedOuterTypes.add(outerClass);
        Map<Type, Type> outerTypeMap = new HashMap<Type, Type>();
        extractTypeArguments(outerTypeMap, outerClass);

        for (Map.Entry<Type, Type> entry : outerTypeMap.entrySet())
        {
          if (!(entry.getKey() instanceof TypeVariable)) {
            continue;
          }

          TypeVariable<?> foundType = (TypeVariable<?>) entry.getKey();
          if (foundType.getName().equals(actualType.getName())
              && isInnerClass(foundType.getGenericDeclaration(), actualType.getGenericDeclaration()))
          {
            if (entry.getValue() instanceof Class) {
              return (Class<?>) entry.getValue();
            }
            actualType = (TypeVariable<?>) entry.getValue();
          }
        }
      }
      catch (NoSuchFieldException e)
      {
        /* this should never happen */ 
      }
      catch (IllegalAccessException e)
      {
        /* this might happen */
      }
    }
    throw new IllegalArgumentException();
  }
      
  private static boolean isInnerClass(
    GenericDeclaration outerDeclaration, GenericDeclaration innerDeclaration)
  {
    if (!(outerDeclaration instanceof Class) || !(innerDeclaration instanceof Class)) {
      throw new IllegalArgumentException();
    }

    Class<?> outerClass = (Class<?>) outerDeclaration;
    Class<?> innerClass = (Class<?>) innerDeclaration;
    while ((innerClass = innerClass.getEnclosingClass()) != null) {
      if (innerClass == outerClass) {
        return true;
      }
    }
    return false;
  }

  private static void extractTypeArguments(Map<Type, Type> typeMap, Class<?> clazz)
  {
    Type genericSuperclass = clazz.getGenericSuperclass();
    if (!(genericSuperclass instanceof ParameterizedType)) {
      return;
    }

    ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
    Type[] typeParameter = ((Class<?>) parameterizedType.getRawType()).getTypeParameters();
    Type[] actualTypeArgument = parameterizedType.getActualTypeArguments();
    for (int i = 0; i < typeParameter.length; i++) {
      if(typeMap.containsKey(actualTypeArgument[i])) {
        actualTypeArgument[i] = typeMap.get(actualTypeArgument[i]);
      }
      typeMap.put(typeParameter[i], actualTypeArgument[i]);
    }
  }
}
