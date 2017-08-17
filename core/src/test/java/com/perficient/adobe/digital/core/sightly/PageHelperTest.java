//package com.perficient.adobe.digital.core.sightly;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.sling.api.resource.ValueMap;
//import org.apache.sling.api.wrappers.ValueMapDecorator;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import com.day.cq.tagging.Tag;
//import com.day.cq.wcm.api.Page;
//
//// TODO: Auto-generated Javadoc
///**
// * The Class PageHelperPageTitleTest.
// */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ PageHelper.class })
//public class PageHelperTest {
//
//    /** The current page. */
//    @Mock
//    private Page currentPage;
//
//    /** The page helper. */
//    @Spy
//    private PageHelper pageHelper = new PageHelper();
//
//    /** The page properties. */
//    private ValueMap pageProperties;
//
//    /**
//     * Setup.
//     */
//    @Before
//    public void setup() {
//        doReturn(currentPage).when(pageHelper).getCurrentPage();
//        when(currentPage.getName()).thenReturn("digital");
//        Map<String, Object> propertiesMap = new HashMap<String, Object>();
//        propertiesMap.put("jcr:description",
//                "Our full-service offering is designed to tackle your ever-changing, ever-increasing business challenges by making sure the executional means never fail the strategic vision.");
//        pageProperties = new ValueMapDecorator(propertiesMap);
//        doReturn(pageProperties).when(pageHelper).getPageProperties();
//        Tag tag = mock(Tag.class);
//        Tag[] tagArray = { tag, tag, tag, tag, tag };
//        when(currentPage.getTags()).thenReturn(tagArray);
//        when(tag.getTitle()).thenReturn("digital", "marketing", "advertising", "agency", "creative");
//    }
//
//    /**
//     * Test description.
//     *
//     * @throws Exception
//     *             the exception
//     */
//    @Test
//    public void testDescription() throws Exception {
//        pageHelper.activate();
//        assertEquals(
//                "Our full-service offering is designed to tackle your ever-changing, ever-increasing business challenges by making sure the executional means never fail the strategic vision.",
//                pageHelper.getDescription());
//
//    }
//
////    /**
////     * Test keywords.
////     */
////    @Test
////    public void testKeywords() throws Exception {
////        pageHelper.activate();
////        assertEquals("digital, marketing, advertising, agency, creative", pageHelper.getKeywords());
////
////    }
//
//    /**
//     * Test title with name.
//     *
//     * @throws Exception
//     *             the exception
//     */
//    @Test
//    public void testTitleWithName() throws Exception {
//        pageHelper.activate();
//        assertEquals("digital", pageHelper.getPageTitle());
//    }
//
//    /**
//     * Test title with page title.
//     *
//     * @throws Exception
//     *             the exception
//     */
//    @Test
//    public void testTitleWithPageTitle() throws Exception {
//        when(currentPage.getTitle()).thenReturn("Digital");
//        when(currentPage.getPageTitle()).thenReturn("Perficient Digital");
//        pageHelper.activate();
//        assertEquals("Perficient Digital", pageHelper.getPageTitle());
//    }
//
//    /**
//     * Test title with title.
//     *
//     * @throws Exception
//     *             the exception
//     */
//    @Test
//    public void testTitleWithTitle() throws Exception {
//        when(currentPage.getTitle()).thenReturn("Digital");
//        pageHelper.activate();
//        assertEquals("Digital", pageHelper.getPageTitle());
//    }
//}