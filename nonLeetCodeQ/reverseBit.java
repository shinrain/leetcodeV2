/*Finally reverse bits of an integer.
*/

    int reverseInt(int val)
    {
        val = (val&(0xAAAAAAAA))>>1|((val&(0x55555555))<<1);
        val = (val&(0xCCCCCCCC))>>2|((val&(0x33333333))<<2);
        val = (val&(0xF0F0F0F0))>>4|((val&(0x0F0F0F0F0F))<<4);
        val = (val&(0xFF00FF00))>>8|((val&(0x00FF00FF))<<8);
        val = (val&(0xFFFF0000))>>16|((val&(0x0000FFFF))<<16);
        return val;
    }