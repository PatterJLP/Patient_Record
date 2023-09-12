
import java.util.Arrays;
import java.util.NoSuchElementException;

public class PriorityCareTester {

  /**
   * Tests whether compareTo() method implemented in PatientRecord returns a positive integer when a
   * higher triage level is compared to a lower triage level, regardless of patient order of
   * arrival. Similarly, this method tests whether compareTo() method implemented in PatientRecord
   * returns a negative integer when a lower triage level is compared to a higher triage level,
   * regardless of patient order of arival.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToDifferentTriage() {
    try {
      //Creates two different patients with different triage levels.
      PatientRecord red = new PatientRecord('M', 19, TriageLevel.RED);
      PatientRecord green = new PatientRecord('M', 21, TriageLevel.GREEN);

      //Green should be higher triage level
      if (green.compareTo(red) <= 0) {
        return false;
      }
      //Red should be higher triage level
      if (red.compareTo(green) >= 0) {
        return false;
      }

      return true;
    }catch (Exception e){return false;}
  }

  /**
   * Tests whether patients in the same triage level are compared based on their order of arrival.
   * Patients of the same triage level with a lower arrival number compared to patients with a
   * higher arrival number should return a negative integer. The reverse situation should return a
   * positive integer.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToSameTriageDifferentArrival() {
    try {
      //Two patients in the same triage level
      PatientRecord first = new PatientRecord('M', 21, TriageLevel.RED);
      PatientRecord second = new PatientRecord('M', 19, TriageLevel.RED);

      //Should be compared off of order of arrival
      if (first.compareTo(second) >= 0) {
        return false;
      }
      if (second.compareTo(first) <= 0) {
        return false;
      }

      return true;
    }catch (Exception e){return false;}
  }

  /**
   * Tests whether patients in the same triage level and with the same order of arrival are equal
   * (compareTo should return 0). Even though this case will not be possible in your priority queue,
   * it is required for testing the full functionality of the compareTo() method. Hint: you will
   * need to use the resetCounter() to create equivalent PatientRecords.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToSameTriageSameArrival() {
    try {
      //Creates two patients with same triage and same order of arrival
      PatientRecord.resetCounter();
      PatientRecord first = new PatientRecord('M', 21, TriageLevel.RED);
      PatientRecord.resetCounter();
      PatientRecord second = new PatientRecord('M', 19, TriageLevel.RED);

      //Should be equal
      if (first.compareTo(second) != 0) {
        return false;
      }
      return true;
    }catch (Exception e){return false;}
  }

  /**
   * Tests the functionality of the constructor for PriorityCareAdmissions Should implement at least
   * the following tests:
   *
   * - Calling the PriorityCareAdmissions with an invalid capacity should throw an
   * IllegalArgumentException
   * - Calling the PriorityCareAdmissions with a valid capacity should not throw any errors, and
   * should result in a new PriorityCareAdmissions which is empty, has size 0, a capacity equal to
   * the capacity that was passed as a parameter.
   *
   * @return true if the constructor of PriorityCareAdmissions functions properly, false otherwise
   * @see PriorityCareAdmissions#PriorityCareAdmissions(int)
   */
  public static boolean testConstructor() {
    PatientRecord.resetCounter();
    try {
      //Tests constructor on invalid capacity
      try {
        PriorityCareAdmissions invalid = new PriorityCareAdmissions(-5);
        return false;
        //Makes sure that it throws IllegalArgumentException
      }catch (IllegalArgumentException e){
      } catch (Exception e){return false;}

      //Tests constructor with valid capacity
      PriorityCareAdmissions test = new PriorityCareAdmissions(5);
      if (!test.isEmpty()){
        return false;
      }
      if (test.size() != 0){
        return false;
      }
      if (test.capacity() != 5){
        return false;
      }


    }catch (Exception e){return false;}

    return true;
  }


  /**
   * Tests the functionality of peek() method by calling peek on an empty queue and verifying it
   * throws a NoSuchElementException.
   *
   * @return true if PriorityCareAdmissions.peek() exhibits expected behavior, false otherwise.
   */
  public static boolean testPeekEmpty() {
    try {
      PatientRecord.resetCounter();
      PriorityCareAdmissions peek = new PriorityCareAdmissions(5);
      //Tests peek on an empty queue should throw exception
      try {
        peek.peek();
        return false;
        //Tests that it returns NoSuchElementException
      }catch (NoSuchElementException e){
      }catch (Exception e){return false;}

      return true;
    }catch (Exception e){return false;}
  }

  /**
   * Tests the functionality of peek() method by calling peek on a non-empty queue and verifying it
   * 1) returns the PatientRecord having the highest priority (the minimum) and 2) does not remove
   * the PatientRecord from the queue.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekNonEmpty() {
    try {
      PatientRecord.resetCounter();

      //Creates non empty queue
      PriorityCareAdmissions peekTest = new PriorityCareAdmissions(5);
      PatientRecord red = new PatientRecord('M',21,TriageLevel.RED);
      PatientRecord yellow = new PatientRecord('M',21,TriageLevel.YELLOW);
      PatientRecord green = new PatientRecord('M',21,TriageLevel.GREEN);
      peekTest.addPatient(red);
      peekTest.addPatient(yellow);
      peekTest.addPatient(green);

      //Should return highest priority
      if (peekTest.peek() != red){
        return false;
      }
      //Size should be 3, does not remove
      if (peekTest.size() != 3){
        return false;
      }

      return true;
    }catch (Exception e){return false;}
  }

  /**
   * Tests the functionality of addPatient() method by calling addPatient() on an empty queue and
   * ensuring the method 1) adds the PatientRecord and 2) increments the size.
   *
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientEmpty() {
    try {
      PatientRecord.resetCounter();
      //Creates an empty queue
      PriorityCareAdmissions test = new PriorityCareAdmissions(5);
      PatientRecord red = new PatientRecord('M',21,TriageLevel.RED);

      //Tests adding patient on an empty queue
      test.addPatient(red);
      if (test.peek() != red){
        return false;
      }
      if (test.size() != 1){
        return false;
      }

      return true;
    }catch (Exception e){return false;}
  }


  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a non-empty queue and
   * ensuring the method 1) adds the PatientRecord at the proper position and 2) increments the
   * size. Try add at least 5 PatientRecords.
   *
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false otherwise
   */
  public static boolean testAddPatientNonEmpty() {
    try {
      PatientRecord.resetCounter();
      //Creates non-empty quee
      PriorityCareAdmissions test = new PriorityCareAdmissions(6);
      PatientRecord red = new PatientRecord('M',21,TriageLevel.RED);
      PatientRecord red2 = new PatientRecord('M',22,TriageLevel.RED);
      PatientRecord yellow = new PatientRecord('F',23,TriageLevel.YELLOW);
      PatientRecord yellow2 = new PatientRecord('M',24,TriageLevel.YELLOW);
      PatientRecord green = new PatientRecord('F',25,TriageLevel.GREEN);
      PatientRecord green2 = new PatientRecord('F',26,TriageLevel.GREEN);
      if (test.size() != 0){
        return false;
      }
      test.addPatient(yellow);
      //Size should be incremented
      if (test.size() != 1){
        return false;
      }

      //Tests adding different patients into a non-empty queue. Should be inserted at front
      test.addPatient(red2);
      if (test.size() != 2){
        return false;
      }
      //Tests with toString method
      String expected = "22203: 22M (RED) - not seen\n" + "12304: 23F (YELLOW) - not seen\n";
      String actual = test.toString();
      if (!expected.equals(actual)){
        return false;
      }

      //Should be inserted at end
      test.addPatient(green);
      if (test.size() != 3){
        return false;
      }
      expected = "22203: 22M (RED) - not seen\n" + "12304: 23F (YELLOW) - not seen\n" + "12506: " +
              "25F (GREEN) - not seen\n";
      actual = test.toString();
      if (!expected.equals(actual)){
        return false;
      }

      //Should be inserted in middle of queue
      test.addPatient(yellow2);
      if (test.size() != 4){
        return false;
      }

      expected = "22203: 22M (RED) - not seen\n" + "12304: 23F (YELLOW) - not seen\n" + "22405: " +
              "24M (YELLOW) - not seen\n" + "12506: 25F (GREEN) - not seen\n";
      actual = test.toString();
      if (!expected.equals(actual)){
        return false;
      }

      //Should be very front of queue
      test.addPatient(red);
      if (test.size() != 5){
        return false;
      }

      expected = "22102: 21M (RED) - not seen\n" + "22203: 22M (RED) - not seen\n" + "12304: 23F " +
              "(YELLOW) - not seen\n" + "22405: 24M (YELLOW) - not seen\n" + "12506: 25F (GREEN) " +
              "- not seen\n";
      actual = test.toString();
      if (!expected.equals(actual)){
        return false;
      }

      //Very end of queue
      test.addPatient(green2);
      if (test.size() != 6){
        return false;
      }

      expected = "22102: 21M (RED) - not seen\n" + "22203: 22M (RED) - not seen\n" + "12304: 23F " +
              "(YELLOW) - not seen\n" + "22405: 24M (YELLOW) - not seen\n" + "12506: 25F (GREEN) " +
              "- not seen\n" + "12607: 26F (GREEN) - not seen\n";
      actual = test.toString();
      if (!expected.equals(actual)){
        return false;
      }
      return true;
    }catch (Exception e){return false;}
  }


  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a full queue and
   * ensuring the method throws an IllegalStateException.
   *
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientFull() {
    try {
      PatientRecord.resetCounter();
      //Creates a full queue
      PriorityCareAdmissions test = new PriorityCareAdmissions(1);
      test.addPatient(new PatientRecord('F',22,TriageLevel.GREEN));

      try {
        //Tries adding patient to a full queue
        test.addPatient(new PatientRecord('M',23,TriageLevel.YELLOW));
        return false;
        //Should throw IllegalStateException
      }catch (IllegalStateException e){
      } catch (Exception e){return false;}


      return true;
    }catch (Exception e){return false;}
  }

  /**
   * Tests the functionality of addPatient() method by calling addPatient() with a null
   * PatientRecord and ensuring the method throws a NullPointerException.
   *
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientNull() {
    try {
      PatientRecord.resetCounter();

      PriorityCareAdmissions test = new PriorityCareAdmissions(1);
      PatientRecord nullPatient = null;
      //Tries adding null patient toa queue
      try {
        test.addPatient(nullPatient);
        return false;
        //Should throw NullPointerException
      }catch (NullPointerException e){
      }catch (Exception e){
        return false;
      }

      return true;
    }catch (Exception e){return false;}
  }


  /**
   * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on an empty
   * queue.
   *
   * @return true if PriorityCareAdmissions.removeBestRecord() throws a NoSuchElementException,
   *         false otherwise
   */
  public static boolean testRemoveBestRecordEmpty() {
    try {
      PatientRecord.resetCounter();
      //Creates empty queue
      PriorityCareAdmissions test = new PriorityCareAdmissions(1);
      //Tries removing
      try {
        test.removeBestRecord();
        return false;
        //Should throw NoSuchElementException
      }catch (NoSuchElementException e){
      }catch (Exception e){
        return false;
      }

      return true; // default return statement added to resolve compiler errors
    }catch (Exception e){return false;}
  }


  /**
   * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on a queue
   * of size one.
   *
   * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord and
   *         size is 0
   */
  public static boolean testRemoveBestRecordSizeOne() {
    try {
      PatientRecord.resetCounter();
      //Creates queue with size one
      PriorityCareAdmissions test = new PriorityCareAdmissions(1);
      PatientRecord one = new PatientRecord('M',21,TriageLevel.GREEN);
      test.addPatient(one);
      if (test.size() != 1){
        return false;
      }

      //Should return only record in queue and size should be zero
      PatientRecord expected = one;
      PatientRecord actual = test.removeBestRecord();
      if (expected != actual){
        return false;
      }
      if (test.size() != 0){
        return false;
      }

      return true; // default return statement added to resolve compiler errors
    }catch (Exception e){return false;}
  }

  /**
   * Tests the functionality of removeBestRecord() methods.
   *
   * The removeBestRecord() method must remove, and return the patient record with the highest
   * priority in the queue. The size must be decremented by one, each time the removeBestRecord()
   * method is successfully called.
   *
   * Remove the best record from a queue whose size is at least 6. Consider cases where
   * percolate-down recurses on left and right.
   *
   * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord
   *         each time it is called and size is appropriately decremented, false otherwise
   */
  public static boolean testRemoveBestRecordNonEmpty() {
    try {
      PatientRecord.resetCounter();

      //Creates queue that is non-empty
      PriorityCareAdmissions test = new PriorityCareAdmissions(6);
      PatientRecord red = new PatientRecord('M',21,TriageLevel.RED);
      PatientRecord red2 = new PatientRecord('M',22,TriageLevel.RED);
      PatientRecord yellow = new PatientRecord('F',23,TriageLevel.YELLOW);
      PatientRecord yellow2 = new PatientRecord('M',24,TriageLevel.YELLOW);
      PatientRecord green = new PatientRecord('F',25,TriageLevel.GREEN);
      PatientRecord green2 = new PatientRecord('F',26,TriageLevel.GREEN);
      test.addPatient(yellow);
      test.addPatient(green2);
      test.addPatient(red);
      test.addPatient(yellow2);
      test.addPatient(green);
      test.addPatient(red2);

      if (test.size() != 6){
        return false;
      }
      //Should remove highest priority
      PatientRecord expected = red;
      PatientRecord actual = test.removeBestRecord();
      if (expected != actual){
        return false;
      }
      //Size should decrement
      if (test.size() != 5){
        return false;
      }
      //Should return highest priority in queue
      expected = red2;
      actual = test.removeBestRecord();
      if (expected != actual){
        return false;
      }
      //Size should decrement
      if (test.size() != 4){
        return false;
      }
      //Should return highest priority in queue
      expected = yellow;
      actual = test.removeBestRecord();
      if (expected != actual){
        return false;
      }
      //Size should decrement
      if (test.size() != 3){
        return false;
      }
      //Should return highest priority in queue
      expected = yellow2;
      actual = test.removeBestRecord();
      if (expected != actual){
        return false;
      }
      //Size should decrement
      if (test.size() != 2){
        return false;
      }
      //Should return highest priority in queue
      expected = green;
      actual = test.removeBestRecord();
      if (expected != actual){
        return false;
      }
      //Size should decrement
      if (test.size() != 1){
        return false;
      }
      //Should return highest priority in queue
      expected = green2;
      actual = test.removeBestRecord();
      if (expected != actual){
        return false;
      }
      //Size should be zero
      if (test.size() != 0){
        return false;
      }

      return true; // default return statement added to resolve compiler errors
    }catch (Exception e){return false;}
  }

  /**
   * Tests the functionality of the clear() method.
   * Should implement at least the following scenarios:
   * - clear can be called on an empty queue with no errors
   * - clear can be called on a non-empty queue with no errors
   * - After calling clear(), the queue should contain zero PatientRecords.
   * - After calling clear(), the size should be 0
   *
   * @return true if PriorityCareAdmissions.clear() functions properly
   */
  public static boolean testClear() {
    try {
      PatientRecord.resetCounter();
      //Creates empty queue
      PriorityCareAdmissions test = new PriorityCareAdmissions(6);
      //Should clear with no errors
      test.clear();

      test.addPatient(new PatientRecord('F',21,TriageLevel.YELLOW));
      if (test.size() != 1){
        return false;
      }
      //Clear should reset size to zero and return an empty string
      test.clear();
      if (test.size() != 0){
        return false;
      }
      if (!test.toString().equals("")){
        return false;
      }
      return true; // default return statement added to resolve compiler errors
    }catch (Exception e){return false;}
  }


  /**
   * Tests toString() method of PriorityCareAdmissions class.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testToString() {
    try {
      PatientRecord.resetCounter();
      //Tests string on an empty queue
      PriorityCareAdmissions test = new PriorityCareAdmissions(6);
      if (!test.toString().equals("")){
        return false;
      }

      //Tests toString which should be in decreasing order of priority
      test.addPatient(new PatientRecord('M',21,TriageLevel.GREEN));
      String expected = "22102: 21M (GREEN) - not seen\n";
      String actual = test.toString();
      if (!expected.equals(actual)){
        return false;
      }
      //Tests toString which should be in decreasing order of priority
      test.addPatient(new PatientRecord('M',21,TriageLevel.YELLOW));
      expected = "22103: 21M (YELLOW) - not seen\n" + "22102: 21M (GREEN) - not seen\n";
      actual = test.toString();
      if (!expected.equals(actual)){
        return false;
      }
      //Tests toString which should be in decreasing order of priority
      test.addPatient(new PatientRecord('M',21,TriageLevel.RED));
      expected = "22104: 21M (RED) - not seen\n" + "22103: 21M (YELLOW) - not seen\n" + "22102: " +
              "21M (GREEN) - not seen\n";
      actual = test.toString();
      if (!expected.equals(actual)){
        return false;
      }


      return true; // default return statement added to resolve compiler errors
    }catch (Exception e){return false;}
  }


  /**
   * Runs all the tester methods defined in this class.
   *
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {

    return testPatientRecordCompareToDifferentTriage()
        && testPatientRecordCompareToSameTriageDifferentArrival()
        && testPatientRecordCompareToSameTriageSameArrival() && testPeekEmpty()
        && testPeekNonEmpty() && testAddPatientEmpty() && testAddPatientNonEmpty()
        && testAddPatientFull() && testAddPatientNull() && testRemoveBestRecordNonEmpty()
        && testRemoveBestRecordEmpty() && testRemoveBestRecordSizeOne() && testClear()
        && testToString();
  }

  /**
   * Main method to run this tester class.
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToDifferentTriage: "
        + (testPatientRecordCompareToDifferentTriage() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToSameTriageDifferentArrival: "
        + (testPatientRecordCompareToSameTriageDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToSameTriageSameArrival: "
        + (testPatientRecordCompareToSameTriageSameArrival() ? "Pass" : "Failed!"));
    System.out.println("testConstructor: " + (testConstructor() ? "Pass" : "Failed!"));
    System.out.println("testPeekEmpty: " + (testPeekEmpty() ? "Pass" : "Failed!"));
    System.out.println("testPeekNonEmpty: " + (testPeekNonEmpty() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientEmpty: " + (testAddPatientEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testAddPatientNonEmpty: " + (testAddPatientNonEmpty() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientFull: " + (testAddPatientFull() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientNull: " + (testAddPatientNull() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordNonEmpty: " + (testRemoveBestRecordNonEmpty() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordEmpty: " + (testRemoveBestRecordEmpty() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordSizeOne: " + (testRemoveBestRecordSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testClear: " + (testClear() ? "Pass" : "Failed!"));
    System.out.println("testToString: " + (testToString() ? "Pass" : "Failed!"));
  }

}
