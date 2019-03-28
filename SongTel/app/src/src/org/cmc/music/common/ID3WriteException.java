package com.jin10.songtel.src.org.cmc.music.common;

public class ID3WriteException extends org.cmc.music.common.ID3Exception
{
	public ID3WriteException(String s)
	{
		super(s);
	}

	public ID3WriteException(String s, Exception e)
	{
		super(s, e);
	}

    public void printStackTrace() {
    }
}
