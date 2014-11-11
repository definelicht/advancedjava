package assignment2;

class SubscriberRegistration {

  private final Subscriber subscriber;
  private final int discomfortLevel;

  public SubscriberRegistration(Subscriber subscriber, int discomfortLevel) {
    this.subscriber = subscriber;
    this.discomfortLevel = discomfortLevel;
  }

  public Subscriber getSubscriber() {
    return subscriber;
  }

  public int getDiscomfortLevel() {
    return discomfortLevel;
  }

}
