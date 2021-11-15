#ifndef GENRANDOMKEY_H
#define GENRANDOMKEY_H

#include <vector>
#include <iostream>

class GenRandomKey
{
private:
	std::vector<char> key;

public:
	GenRandomKey();
	std::vector<char> GetKey();
	
};

#endif
