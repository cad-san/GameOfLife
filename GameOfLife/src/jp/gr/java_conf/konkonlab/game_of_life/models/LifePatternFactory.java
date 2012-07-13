package jp.gr.java_conf.konkonlab.game_of_life.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Pair;

public class LifePatternFactory {
	private static final String TAG_LIFE    = "life";
	private static final String TAG_NAME    = "name";
	private static final String TAG_PATTERN = "pattern";
	private static final String TAG_CELL    = "cell";
	private static final String ATTR_NUM    = "no";
	private static final String ATTR_TYPE   = "type";
	private static final String ATTR_WIDTH  = "width";
	private static final String ATTR_HEIGHT = "height";
	private static final String ATTR_X      = "x";
	private static final String ATTR_Y      = "y";

	
	List<XmlResourceParser> parsers = new ArrayList<XmlResourceParser>();
	public void addParser(XmlResourceParser parser) {
		parsers.add(parser);
	}
	
	public List<LifePattern> parseLifePatterns() {
		List<LifePattern> patterns = new ArrayList<LifePattern>();

		Iterator<XmlResourceParser> it = parsers.iterator();
		
		while(it.hasNext()) {
			XmlResourceParser parser = (XmlResourceParser) it.next();
			try {
				patterns.add(parseLifePatternsFromXML(parser));
			}
			catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return patterns;
	}

	private LifePattern parseLifePatternsFromXML(XmlResourceParser parser)
			throws XmlPullParserException, IOException {
		LifePattern pattern = null;

		Log.d("XML:ELEM", "START");
		for(int tag = parser.getEventType(); tag != XmlResourceParser.END_DOCUMENT; tag = parser.next()) {
			if(isEndTagOf(TAG_LIFE, parser, tag)) {
				break;
			}
			if(isStartTagOf(TAG_LIFE, parser, tag)) {
				Log.d("XML:ELEM_NAME", parser.getName());
				pattern = parseLife(parser);
			}
		}
		Log.d("XML:ELEM", "END");
		return pattern;
	}

	private LifePattern parseLife(XmlResourceParser parser)
			throws XmlPullParserException, IOException {

		String name = "";
		int number = -1;
		int type = 0;
		int width = 0;
		int height = 0;
		List<Pair<Integer, Integer>> cells = null;

		if(parser.getAttributeCount() == 2) {
			for(int attr = 0; attr < parser.getAttributeCount(); attr++) {
				// 属性情報
				if(isAttributeOf(ATTR_NUM, parser, attr)) {
					number = Integer.valueOf(parser.getAttributeValue(attr));
				}
				if(isAttributeOf(ATTR_TYPE, parser, attr)) {
					type = Integer.valueOf(parser.getAttributeValue(attr));
				}
				Log.d("XML:ELEM_ATTR", parser.getAttributeName(attr) + " = " + parser.getAttributeValue(attr));
			}
		}
		else {
			throw new XmlPullParserException("wrong attributes in <life></life>");
		}
		
		for(int tag = parser.getEventType(); tag != XmlResourceParser.END_DOCUMENT; tag = parser.next()) {
			if(isEndTagOf(TAG_LIFE, parser, tag)) {
				/* </life>で終了 */
				break;
			}
			if(isStartTagOf(TAG_NAME, parser, tag)) {
				/* <name></name>のParse */
				Log.d("XML:ELEM_NAME", parser.getName());
				tag = parser.next();
				if(tag == XmlResourceParser.TEXT){
					name = parser.getText();
					Log.d("XML:ELEM_NAME", parser.getText());
				}
			}
			if(isStartTagOf(TAG_PATTERN, parser, tag)) {
				Log.d("XML:ELEM_NAME", parser.getName());
				if( parser.getAttributeCount() == 2 ) {
					for( int attr = 0; attr < parser.getAttributeCount(); attr++ ) {
						/* 属性取得 */
						if(isAttributeOf(ATTR_WIDTH, parser, attr)) {
							width = Integer.valueOf(parser.getAttributeValue(attr));
						}
						if(isAttributeOf(ATTR_HEIGHT, parser, attr)) {
							height = Integer.valueOf(parser.getAttributeValue(attr));
						}
						Log.d("XML:ELEM_ATTR", parser.getAttributeName(attr) + " = " + parser.getAttributeValue(attr));
					}
				}
				else {
					throw new XmlPullParserException("wrong attributes in <pattern></pattern>");
				}
				cells = parsePattern(parser);
			}
		}
		return new LifePattern(name, type, cells, width, height);
	}

	private List<Pair<Integer, Integer>> parsePattern(XmlResourceParser parser)
			throws XmlPullParserException, IOException {

		List<Pair<Integer, Integer>> cells = new ArrayList<Pair<Integer, Integer>>();
		int x = -1;
		int y = -1;
		
		for(int tag = parser.getEventType(); tag != XmlResourceParser.END_DOCUMENT; tag = parser.next()) {
			if(isEndTagOf(TAG_PATTERN, parser, tag)){
				/* </pattern>で終了 */
				Log.d("XML:ELEM", "END");
				break;
			}
			if(isStartTagOf(TAG_CELL, parser, tag)){
				/* <cells>のParse */
				Log.d("XML:ELEM_NAME", parser.getName());
				if(parser.getAttributeCount() == 2) {
					for(int attr = 0; attr < parser.getAttributeCount(); attr++) {
						/* 属性取得 */
						if(isAttributeOf(ATTR_X, parser, attr)) {
							x = Integer.valueOf(parser.getAttributeValue(attr));
						}
						if(isAttributeOf(ATTR_Y, parser, attr)) {
							y = Integer.valueOf(parser.getAttributeValue(attr));
						}
						Log.d("XML:ELEM_ATTR", parser.getAttributeName(attr) + " = " + parser.getAttributeValue(attr));
					}
					cells.add(new Pair<Integer, Integer>(x, y));
				}
				else {
					throw new XmlPullParserException("wrong attribute in <cell />");
				}
				Log.d("Cell", "cell : " + x + ", " + y);
			}
		}
		return cells;
	}

	private boolean isEndTagOf(String tagName, XmlResourceParser parser, int tagNumber) {
		return tagNumber == XmlResourceParser.END_TAG && tagName.equals(parser.getName());
	}

	private boolean isStartTagOf(String tagName, XmlResourceParser parser, int tagNumber) {
		return tagNumber == XmlResourceParser.START_TAG && tagName.equals(parser.getName());
	}

	private boolean isAttributeOf(String attrName, XmlResourceParser parser, int attr) {
		return attrName.equals(parser.getAttributeName(attr));
	}
}
