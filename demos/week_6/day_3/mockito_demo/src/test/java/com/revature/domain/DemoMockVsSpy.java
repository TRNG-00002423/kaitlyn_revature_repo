package com.revature.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

// mock: completely fake - all methods return defaults unless stubbed
// spy: real object wrapper - real methods execute unless stubbed

// use mock for: complete isolation, testing interactions
// use spy for: partial mocking, legacy code, need somne real behavior

@ExtendWith(MockitoExtension.class)
public class DemoMockVsSpy {

    // spy behavior
    @Spy
    private List<String> spyList = new ArrayList<>();

    @Test
    @DisplayName("Spy: real methods execute")
    void demonstrateSpyBehavior() {
        // spy wraps a real ArrayList, so methods actually work.

        spyList.add("item 1");
        spyList.add("item 2");

        // real behavior: items were actually added
        assertEquals(2, spyList.size(), "spy actually has items");
        assertEquals("item 1", spyList.get(0));
        assertEquals("item 2", spyList.get(1));
    }

    @Test
    @DisplayName("Spy can selectively override methods")
    void demonstrateSpySelectiveStubbing() {
        // create fresh spy
        List<String> freshSpy = spy(new ArrayList<>());

        freshSpy.add("real 1");
        freshSpy.add("real 2");

        assertEquals(2, freshSpy.size(), "real size before stubbing");

        // stub just the size method
        when(freshSpy.size()).thenReturn(100);

        // size is stubbed, but data is still real
        assertEquals(100, freshSpy.size());
        assertEquals("real 1", freshSpy.get(0));
        assertEquals("real 2", freshSpy.get(1));
    }

    // side by side comparison
    @Test
    @DisplayName("Comparison: Mock vs. Spy Behavior")
    void compareMockAndSpy() {
        // create both types
        List<String> mock = Mockito.mock(ArrayList.class);
        List<String> spy = spy(new ArrayList<>());

        mock.add("item");
        spy.add("item");

        assertEquals(0, mock.size(), "mock didn't actually add");
        assertEquals(1, spy.size(), "spy actually added");

        assertNull(mock.get(0));
        assertEquals("item", spy.get(0));
    }

}
