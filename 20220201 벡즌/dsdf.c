#include <stdio.h>
int main(void)
{
	int n;
	int i = 0, new = 0, sum = 0, re=0;
	scanf_s("%d", &n);

	re = n;
	while (1)
	{
		sum = (re / 10) + (re % 10);
		new = (re % 10) * 10 + (sum % 10);
		re = new;
		i++;
		if (n == new)
			break;
	}
	printf("%d", i);
	return 0;
}