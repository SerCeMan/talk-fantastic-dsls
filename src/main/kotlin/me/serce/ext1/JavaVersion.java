package me.serce.ext1;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by serce on 23/4/17.
 */
public final class JavaVersion {

  @NotNull
  private static final Droid droid = new Droid(true, new Gun());
  @Nullable
  private static final Void currentCmd = null;

  public static String removeSpaces(String $receiver) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < $receiver.length(); i++) {
      char c = $receiver.charAt(i);
      if (c != ' ') {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public static String map(String $receiver, Function1 f) {
    char[] arr = $receiver.toCharArray();
    ArrayList dest = new ArrayList(arr.length);
    for (int i = 0; i < arr.length; ++i) {
      char c1 = arr[i];
      Character c2 = (Character) f.invoke(c1);
      dest.add(c2);
    }
//        return CollectionsKt.joinToString(dest, "");
    return null;
  }

static class SomeFunction
  implements Function1<Droid, Unit> {
  static SomeFunction INSTANCE = new SomeFunction();

  @Override
  public Unit invoke(Droid droid) {
    droid.moveLeft();
    if (droid.getPeopleAround()) {
      droid.fire(droid.getGun());
    }
    return Unit.INSTANCE;
  }
}

static void on(String cmd,
               Function1<Droid, Unit> f) {
  if (Intrinsics.areEqual(cmd, currentCmd)) {
    f.invoke(droid);
  }
}

public static void main() {
  on("back", SomeFunction.INSTANCE);
}
}

