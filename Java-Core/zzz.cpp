#include <iostream>
using namespace std;

int main() {
    int n = 10;

    bool isPrime = true;
    for (int i = 2; i * i < n; i++) {
        if (n % i == 0) {
            isPrime = false;
            break;
        }
    }

    if (isPrime && n > 1) {
        cout << n << " is a prime number." << endl;
    } else {
        cout << n << " is not a prime number." << endl;
    }


}