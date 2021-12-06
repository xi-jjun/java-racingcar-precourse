package racingcar;

import java.util.ArrayList;
import java.util.List;

import racingcar.domain.Car;
import racingcar.constants.Constant;
import racingcar.constants.Message;
import racingcar.service.UserInputService;

public class GameHandler {
	private final UserInputService userInputService = UserInputService.getInstance();

	public void run() {
		List<Car> cars = createCars();
		int times = userInputService.howManyTimes();
		executeResult(times, cars);
		showWinners(cars);
	}

	private void executeResult(int times, List<Car> cars) {
		System.out.println(Message.EXECUTE_RESULT);

		while (times-- > 0) {
			executeStage(cars);
		}
	}

	private void executeStage(List<Car> cars) {
		List<Car> movedCars = moveAllCars(cars);
		showEachStageResult(movedCars);
	}

	private void showWinners(List<Car> cars) {
		List<Car> winnerCars = findWinnerCars(cars);

		System.out.print(Message.WINNERS);
		StringBuilder winners = getWinners(winnerCars);
		System.out.println(winners);
	}

	private StringBuilder getWinners(List<Car> winnerCars) {
		StringBuilder winners = new StringBuilder();
		for (int i = 0; i < winnerCars.size() - 1; i++) {
			winners.append(winnerCars.get(i).name())
					.append(Constant.SPLIT_STRING)
					.append(Constant.SPACE);
		}
		winners.append(winnerCars.get(winnerCars.size() - 1).name());
		return winners;
	}

	private List<Car> findWinnerCars(List<Car> cars) {
		List<Car> winners = new ArrayList<>();
		int max = maxCurrentPosition(cars);
		for (Car car : cars) {
			if (car.currentPosition() == max) {
				winners.add(car);
			}
		}

		return winners;
	}

	private int maxCurrentPosition(List<Car> cars) {
		return cars.stream()
				.mapToInt(Car::currentPosition)
				.max().orElseThrow(IllegalArgumentException::new);
	}

	private List<Car> moveAllCars(List<Car> cars) {
		for (Car car : cars) {
			car.go();
		}

		return cars;
	}

	private void showEachStageResult(List<Car> cars) {
		for (Car car : cars) {
			showCurrentPosition(car);
		}
		System.out.println("");
	}

	private void showCurrentPosition(Car car) {
		System.out.print(car.name() + " : ");
		for (int i = 0; i < car.currentPosition(); i++) {
			System.out.print("-");
		}
		System.out.println("");
	}

	private List<Car> createCars() {
		System.out.println(Message.START);

		List<Car> carList = new ArrayList<>();
		String[] names = userInputService.register();
		for (String name : names) {
			carList.add(new Car(name));
		}

		return carList;
	}
}
