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
package io.reactivex.rxjava3.swt.listener.control;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.swt.listener.util.Preconditions;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Control;

import io.reactivex.rxjava3.swt.MainThreadDisposable;

public abstract class ControlObservable extends Observable<ControlEvent> {

	private Control control;

	public ControlObservable(Control control) {
		this.control = control;
	}

	@Override
	protected void subscribeActual(Observer<? super ControlEvent> observer) {
		if (!Preconditions.checkWidget(observer, control)) {
			return;
		}
		AbstractControlListener listener = getControlListener(control, observer);
		observer.onSubscribe(listener);
		control.addDisposeListener(e -> listener.dispose());
		control.addControlListener(listener);

	}

	protected abstract AbstractControlListener getControlListener(Control control,
			Observer<? super ControlEvent> observer);

	static abstract class AbstractControlListener extends MainThreadDisposable implements ControlListener {
		private final Control control;

		AbstractControlListener(Control control) {
			this.control = control;
		}

		@Override
		protected void onDispose() {
			control.removeControlListener(this);
		}

		@Override
		public void controlMoved(ControlEvent e) {
		}

		@Override
		public void controlResized(ControlEvent e) {
		}
	}
}
