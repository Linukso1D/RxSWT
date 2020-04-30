/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.reactivex.rxjava3.swt.listener.util;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;

import io.reactivex.rxjava3.core.Observer;

public class Preconditions {
	public static boolean checkWidget(Observer<?> observer, Widget widget) {
		if (null == widget) {
			observer.onError(new NullPointerException("The given widget was null"));
			return false;
		} else if (widget.isDisposed()) {
			observer.onError(new IllegalStateException("The given widget is diposed"));
			return false;
		} else if (!(Thread.currentThread().equals(widget.getDisplay().getThread()))) {
			observer.onError(new IllegalStateException(
					"Expected to be called on the main thread but was " + Thread.currentThread().getName()));
			return false;
		}
		return true;
	}

	public static boolean checkDisplay(Observer<?> observer, Display display) {
		if (null == display) {
			observer.onError(new NullPointerException("The given widget was null"));
			return false;
		} else if (display.isDisposed()) {
			observer.onError(new IllegalStateException("The given widget is diposed"));
			return false;
		} else if (!(Thread.currentThread().equals(display.getThread()))) {
			observer.onError(new IllegalStateException(
					"Expected to be called on the main thread but was " + Thread.currentThread().getName()));
			return false;
		}
		return true;
	}

	private Preconditions() {
	}
}
