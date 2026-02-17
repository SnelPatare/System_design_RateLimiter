public class RateLimiter {
    private final int capacity;
    private final int refillRate; // tokens per second
    private double tokens;
    private long lastRefillTime;

    public RateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;
        this.lastRefillTime = System.nanoTime();
    }

    public synchronized boolean allowRequest() {
        refillTokens();
        if (tokens >= 1) {
            tokens--;
            return true;
        }
        return false;
    }

    private void refillTokens() {
        long now = System.nanoTime();
        double elapsedSeconds = (now - lastRefillTime) / 1_000_000_000.0;
        tokens = Math.min(capacity, tokens + elapsedSeconds * refillRate);
        lastRefillTime = now;
    }
}
