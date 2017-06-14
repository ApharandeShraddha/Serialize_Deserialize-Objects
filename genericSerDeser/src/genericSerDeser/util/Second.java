package genericSerDeser.util;

public class Second {
	
	private double DoubleValue;
	private double DoubleValue2;
	private long LongValue;
	private long LongValue2;
	private short ShortValue;
	private short ShortValue2;
	private String StringValue;	
	
	//Empty constructor
	public Second() {

	}

	public double getDoubleValue() {
		return DoubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		DoubleValue = doubleValue;
	}

	public double getDoubleValue2() {
		return DoubleValue2;
	}

	public void setDoubleValue2(double doubleValue2) {
		DoubleValue2 = doubleValue2;
	}

	public long getLongValue() {
		return LongValue;
	}

	public void setLongValue(long longValue) {
		LongValue = longValue;
	}

	public long getLongValue2() {
		return LongValue2;
	}

	public void setLongValue2(long longValue2) {
		LongValue2 = longValue2;
	}

	public short getShortValue() {
		return ShortValue;
	}

	public void setShortValue(short shortValue) {
		ShortValue = shortValue;
	}

	public short getShortValue2() {
		return ShortValue2;
	}

	public void setShortValue2(short shortValue2) {
		ShortValue2 = shortValue2;
	}

	public String getStringValue() {
		return StringValue;
	}

	public void setStringValue(String stringValue) {
		StringValue = stringValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(DoubleValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(DoubleValue2);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (LongValue ^ (LongValue >>> 32));
		result = prime * result + (int) (LongValue2 ^ (LongValue2 >>> 32));
		result = prime * result + ShortValue;
		result = prime * result + ShortValue2;
		result = prime * result + ((StringValue == null) ? 0 : StringValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Second))
			return false;
		Second other = (Second) obj;
		if (Double.doubleToLongBits(DoubleValue) != Double.doubleToLongBits(other.DoubleValue))
			return false;
		if (Double.doubleToLongBits(DoubleValue2) != Double.doubleToLongBits(other.DoubleValue2))
			return false;
		if (LongValue != other.LongValue)
			return false;
		if (LongValue2 != other.LongValue2)
			return false;
		if (ShortValue != other.ShortValue)
			return false;
		if (ShortValue2 != other.ShortValue2)
			return false;
		if (StringValue == null) {
			if (other.StringValue != null)
				return false;
		} else if (!StringValue.equals(other.StringValue))
			return false;
		return true;
	}
}
