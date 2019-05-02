/**
 * 
 */
package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Exercise;
import model.ReadAndWriteCSV;
import model.Workout;

/**
 * @author Pargan
 *
 */
class ReadAndWriteCSV_test {

	private static Exercise e1 = new Exercise("Liegestuetz","Trizeps",15); 
	private static Exercise e2 = new Exercise("Klimmz�ge","Trizeps",15);
	private static Exercise e3 = new Exercise("Sit-Ups","Trizeps",15);
	private static Exercise e4 = new Exercise("Kniebeugen","Trizeps",15);
	private static Exercise e5 = new Exercise("Streckspr�nge","Trizeps",15);
	
	
	/**
	 * Test method for {@link model.ReadAndWriteCSV#readWorkoutsFromCsv(java.lang.String)}.
	 */
	@Test
	final void testReadWorkoutsFromCsv() {
		ReadAndWriteCSV rw = new ReadAndWriteCSV();
		List<Exercise> el = new LinkedList<Exercise>();
		el.add(e1); el.add(e2); el.add(e3);
		Workout w1 = new Workout("Oberk�rpertraining", "Thu May 02 07:29:00 CEST 2019", el);
		List<Exercise> e = new LinkedList<Exercise>();
		e.add(e4); e.add(e5);
		Workout w2 = new Workout("Beine", "Thu May 02 07:29:00 CEST 2019", e);
		List<Workout> w = new LinkedList<Workout>();
		w.add(w1); w.add(w2);
		List<Workout> workouts;
		try {
			workouts = rw.readWorkoutsFromCsv("workouts.csv");
		} catch (IOException e) {
			
			e.printStackTrace();
			fail("Something went wrong!");
		}
		assertEquals(workouts, w);
	}

	/**
	 * Test method for {@link model.ReadAndWriteCSV#readExercisesFromCsv(java.lang.String)}.
	 */
	@Test
	final void testReadExercisesFromCsv() {
		fail("Not yet implemented"); // TODO
	}

}
