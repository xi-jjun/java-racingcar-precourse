package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.constants.Constant;

public class Car {
	private final String name;
	private int position = 0;

	public Car(String name) {
		this.name = name;
	}

	public int currentPosition() {
		return position;
	}

	public void go() {
		int condition = Randoms.pickNumberInRange(Constant.MIN_CONDITION, Constant.MAX_CONDITION);

		if (condition >= 4) {
			position++;
		}
	}
}
