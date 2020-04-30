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
package io.reactivex.rxjava3.swt.listener.focus;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Control;

import io.reactivex.rxjava3.core.Observer;

public class FocusGainedObservable extends FocusObservable {

	public FocusGainedObservable(Control control) {
		super(control);
	}

	@Override
	protected AbstractFocusListener getFocusListener(Control control, Observer<? super FocusEvent> observer) {
		return new AbstractFocusListener(control) {
			@Override
			public void focusGained(FocusEvent e) {
				if (!isDisposed()) {
					observer.onNext(e);
				}
			}
		};
	}

}
