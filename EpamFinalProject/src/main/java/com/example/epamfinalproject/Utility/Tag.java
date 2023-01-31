package com.example.epamfinalproject.Utility;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Tag extends TagSupport {
  private static final Logger log = LogManager.getLogger(Tag.class);

  @Override
  public int doStartTag() {
    log.debug(Constants.COMMAND_STARTS);
    JspWriter out = pageContext.getOut();
    if (pageContext.getSession().getAttribute("locale") != "ua") {
      try {
        out.print("Сruise Сompany");
      } catch (Exception e) {
        log.warn(e);
      }
    } else {
      try {
        out.print("Круїзна Компанія");
      } catch (Exception e) {
        log.warn(e);
      }
    }
    log.debug(Constants.COMMAND_FINISHED);
    return SKIP_BODY;
  }

  @Override
  public int doEndTag() {
    return EVAL_PAGE;
  }
}
