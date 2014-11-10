package assignment1;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class AggregationTest extends MyTest {

  // dataSet() is implemented in MyTest

  private void testAggregation(Aggregation<Employee> aggregation) {
    List<Employee> list = dataSet();
    int result = aggregation.aggregate(new AddSalary(), list);
    org.junit.Assert.assertTrue(result == 1000);
  }

  @Test
  public void testAggregationSequential() {
    testAggregation(new AggregationSequential<Employee>());
  }

  @Test
  public void testAggregationParallel() {
    testAggregation(new AggregationParallel<Employee>());
  }

}
