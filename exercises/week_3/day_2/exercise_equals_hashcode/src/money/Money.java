package money;

import java.util.Objects;

/**
 * TODO: immutable currency + amountMinor; equals/hashCode contract.
 */
public final class Money {
    private String currency;
    private long amountMinor;

    public Money(String currency, long amountMinor) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null.");
        }
        this.currency = currency;
        this.amountMinor = amountMinor;
    }

    public String getCurrency() {
        return this.currency;
    }

    public long getAmountMinor() {
        return this.amountMinor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Money other = (Money) o;
        return other.currency == this.currency && other.amountMinor == this.amountMinor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.currency, this.amountMinor);
    }

    @Override
    public String toString() {
        return this.amountMinor + this.currency;
    }
}