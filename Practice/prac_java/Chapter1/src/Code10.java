
public class Code10 {

	public static void main(String[] args) {

		//1 ~ 100000 사이의 모든 소수를 찾아내어라

		for (int n = 2; n < 100000; n++)
		{
			boolean isPrime = true;
			for (int i = 2; i * i <= n && isPrime; i++)
			{
				if(n % i == 0)
				{
					isPrime = false;
				}
			}

			if(isPrime)
			{
				System.out.println(n);
			}
		}
		
	}
}
