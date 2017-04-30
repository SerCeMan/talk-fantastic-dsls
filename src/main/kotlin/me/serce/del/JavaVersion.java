package me.serce.del;

import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/**
 * Created by serce on 23/4/17.
 */
public class JavaVersion {
public static class MyWorld {
  static final KProperty[] $$delegProps = new KProperty[]{
    (KProperty) Reflection.property1(/* kotlin magic */ null)
  };
  private final DI niffler$del;

  public MyWorld() {
    this.niffler$del = DI.INSTANCE;
  }

  public Creature getNiffler() {

    return null; //niffler$del.getValue(this, $$delegProps[0]);
  }
}
}
