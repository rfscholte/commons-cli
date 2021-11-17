package org.apache.commons.cli.bug;

import static org.junit.Assert.assertEquals;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Test;

public class BugCLI312Test
{
    @Test 
    public void testOptionProperties() throws Exception
    {
        Option property  = Option.builder("D")
                        .longOpt( "define" )
                        .hasArgs()
                        .valueSeparator('=')
                        .build();
        
        DefaultParser parser = new DefaultParser();
        CommandLine clShort = parser.parse( new Options().addOption( property ), "-Dv -Dw=1 -D x=2 -D y -D z=3".split( " " ) );
        assertEquals( "true", clShort.getOptionProperties( property ).getProperty( "v" ) );
        assertEquals( "1", clShort.getOptionProperties( property ).getProperty( "w" ) );
        assertEquals( "2", clShort.getOptionProperties( property ).getProperty( "x" ) );
        assertEquals( "true", clShort.getOptionProperties( property ).getProperty( "y" ) );
        assertEquals( "3", clShort.getOptionProperties( property ).getProperty( "z" ) );

        CommandLine clLong = parser.parse( new Options().addOption( property ), "--define x=2 --define y --define z=3".split( " " ) );
        assertEquals( "2", clLong.getOptionProperties( property ).getProperty( "x" ) );
        assertEquals( "true", clLong.getOptionProperties( property ).getProperty( "y" ) );
        assertEquals( "3", clLong.getOptionProperties( property ).getProperty( "z" ) );
    }

}
