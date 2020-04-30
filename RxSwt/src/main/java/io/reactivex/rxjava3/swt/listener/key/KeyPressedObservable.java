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
package io.reactivex.rxjava3.swt.listener.key;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Control;

import io.reactivex.rxjava3.core.Observer;

public class KeyPressedObservable extends KeyObservable {

	public KeyPressedObservable(Control control) {
		super(control);
	}

	@Override
	protected AbstractKeyListener getMouseListener(Control control, Observer<? super KeyEvent> observer) {
		return new AbstractKeyListener(control) {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!isDisposed()) {
					observer.onNext(e);
				}
			}
		};
	}

}
