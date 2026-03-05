package ch02_java_12_17.ch02_04_records_beyond_the_basics;

import java.util.Date;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class RecordImmutabilityExample3
{
    public static void main(final String[] args)
    {
		record DateRange(Date start, Date end) 
		{
			DateRange
			{
				if (!start.before(end))
					throw new IllegalArgumentException("start >= end");
			}
		}
				
		DateRange range1 = new DateRange(new Date(71,1,7), new Date(71,2,27));
		System.out.println(range1);
		
		range1.start.setTime(new Date(71,6,7).getTime());
		System.out.println(range1);	
    }
}
