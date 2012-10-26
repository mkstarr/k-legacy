package org.kframework.kil;

import org.kframework.kil.loader.Constants;
import org.kframework.kil.visitors.Modifier;
import org.kframework.kil.visitors.Transformer;
import org.kframework.kil.visitors.Visitor;
import org.kframework.kil.visitors.exceptions.TransformerException;
import org.kframework.utils.StringUtil;
import org.w3c.dom.Element;

/**
 * Used as a container for unparsed sentences like rule, context and configuration.
 * 
 * @author Radu
 * 
 */
public class StringSentence extends ModuleItem {
	private String content;
	private String type;

	public StringSentence(Element element) {
		super(element);
		content = StringUtil.unescape(element.getAttribute(Constants.VALUE_value_ATTR));
		type = element.getNodeName();
	}

	public StringSentence(StringSentence node) {
		super(node);
		this.content = node.content;
	}

	public String toString() {
		return "String sentence.";
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public ASTNode accept(Transformer visitor) throws TransformerException {
		return visitor.transform(this);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void applyToAll(Modifier visitor) {
	}

	@Override
	public StringSentence shallowCopy() {
		return new StringSentence(this);
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
