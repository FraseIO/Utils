// This should work whether your running your server on OSX or Linux.  The reason I had to do it this way was to get around a bug in boilerpipe's HtmlFetcher and a problem I was having with JSOUP as well.

import de.l3s.boilerpipe.BoilerpipeExtractor;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.CommonExtractors;
import de.l3s.boilerpipe.sax.HTMLHighlighter;

import org.apache.commons.io.IOUtils;

URL urlObj = new URL((String) requestJSON.get("url"));
final HttpURLConnection httpcon = (HttpURLConnection) urlObj.openConnection();
httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
httpcon.setInstanceFollowRedirects(true);

InputStream in = httpcon.getInputStream();
String html = IOUtils.toString(in, "UTF-8");
IOUtils.closeQuietly(in);

htmlDoc = new HTMLDocument(html.getBytes(), Charset.defaultCharset());
TextDocument resultHtml = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();

BoilerpipeExtractor extractor = CommonExtractors.ARTICLE_EXTRACTOR;
extractor.process(resultHtml);
final InputSource is = htmlDoc.toInputSource();

final HTMLHighlighter hh = HTMLHighlighter.newExtractingInstance();
hh.setOutputHighlightOnly(true);
String full_html = hh.process(resultHtml, is);
