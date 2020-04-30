package io.reactivex.rxjava3.swt.testutil;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.observers.TestObserver;

public class TestObserverUtil {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TestObserver<?> getEmptySubscribedTestObserver() {
		Observable<?> empty = Observable.empty();
		TestObserver<?> testObserver = TestObserver.create();
		empty.subscribe((Observer) testObserver);
		return testObserver;
	}

	private TestObserverUtil() {
	}
}
