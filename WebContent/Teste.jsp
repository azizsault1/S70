
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootstrap, from Twitter</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le styles -->
    <link href="<%=request.getContextPath()%>/css/a.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/css/b.css" media="screen" rel="stylesheet" type="text/css" />

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" type="image/x-icon" href="/assets/ico/favicon.ico">
    <link rel="apple-touch-icon" href="/assets/ico/bootstrap-apple-57x57.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/assets/ico/bootstrap-apple-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/assets/ico/bootstrap-apple-114x114.png">


    <meta content="authenticity_token" name="csrf-param" />
	<meta content="qVKDiEFrM6lsgjg9O/IOGK1xYNBUvAufTsETSDV2jbc=" name="csrf-token" />
  </head>

  <body>

    <!-- Topbar
    ================================================== -->
    <div class="topbar">
      <div class="topbar-inner">
        <div class="container">
          <a class="brand" href="http://bootstrap.stage42.net/">Bootstrap</a>
          <ul class="nav">
            <li  > <a href="/doc/overview">Overview</a> </li>
            <li  > <a href="/doc/about">About</a> </li>
            <li  > <a href="/doc/grid">Grid</a> </li>
            <li  > <a href="/doc/layouts">Layouts</a> </li>
            <li  > <a href="/doc/typo">Type</a> </li>
            <li  > <a href="/doc/media">Media</a> </li>
            <li  > <a href="/doc/tables">Tables</a> </li>
            <li class="active" > <a href="/doc/forms">Forms</a> </li>
            <li  > <a href="/doc/navigation">Navigation</a> </li>
            <li  > <a href="/doc/alerts">Alerts</a> </li>
            <li  > <a href="/doc/popovers">Popovers</a> </li>
            <li  > <a href="/doc/javascript">Javascript</a> </li>
            <li  > <a href="/doc/less">Less</a> </li>
          </ul>
        </div>
      </div>
    </div>

    <!-- Masthead (blueprinty thing)
    ================================================== -->
    <header class="jumbotron masthead" id="overview">
      <div class="inner">
        <div class="container">
          <h1>Bootstrap, from Twitter</h1>
          <p class="lead">
            Bootstrap is a toolkit from Twitter designed to kickstart development of webapps and sites.<br />
            It includes base CSS and HTML for typography, forms, buttons, tables, grids, navigation, and more.<br />
          </p>
          <p><strong>Nerd alert:</strong> Bootstrap is <a href="#less" title="Read about using Bootstrap with Less">built with Less</a> and was designed to work out of the gate with modern browsers in mind.</p>
        </div><!-- /container -->
      </div>
    </header>

      <div class="container">
          <section id="forms">
  <div class="page-header">
    <h1>Forms</h1>
  </div>
  <div class="row">
    <div class="span4">
      <h2>Default styles</h2>
      <p>All forms are given default styles to present them in a readable and scalable way. Styles are provided for text inputs, select lists, textareas, radio buttons and checkboxes, and buttons.</p>
    </div>
    <div class="span12">
      <form>
        <fieldset>
          <legend>Example form legend</legend>
          <div class="clearfix">
            <label for="xlInput">X-Large input</label>
            <div class="input">
              <input class="xlarge" id="xlInput" name="xlInput" size="30" type="text" />
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label for="normalSelect">Select</label>
            <div class="input">
              <select name="normalSelect" id="normalSelect">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
              </select>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label for="mediumSelect">Select</label>
            <div class="input">
              <select class="medium" name="mediumSelect" id="mediumSelect">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
              </select>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label for="multiSelect">Multiple select</label>
            <div class="input">
              <select class="medium" size="5" multiple="multiple" name="multiSelect" id="multiSelect">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
              </select>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label>Uneditable input</label>
            <div class="input">
              <span class="uneditable-input">Some value here</span>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label for="disabledInput">Disabled input</label>
            <div class="input">
              <input class="xlarge disabled" id="disabledInput" name="disabledInput" size="30" type="text" placeholder="Disabled input here… carry on." disabled />
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label for="disabledInput">Disabled textarea</label>
            <div class="input">
              <textarea class="xxlarge" name="textarea" id="textarea" rows="3" disabled></textarea>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix error">
            <label for="errorInput">Input with error</label>
            <div class="input">
              <input class="xlarge error" id="errorInput" name="errorInput" size="30" type="text" />
              <span class="help-inline">Small snippet of help text</span>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix success">
            <label for="successInput">Input with success</label>
            <div class="input">
              <input class="xlarge error" id="successInput" name="successInput" size="30" type="text" />
              <span class="help-inline">Success!</span>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix warning">
            <label for="warningInput">Input with warning</label>
            <div class="input">
              <input class="xlarge error" id="warningInput" name="warningInput" size="30" type="text" />
              <span class="help-inline">Ruh roh!</span>
            </div>
          </div><!-- /clearfix -->
        </fieldset>
        <fieldset>
          <legend>Example form legend</legend>
          <div class="clearfix">
            <label for="prependedInput">Prepended text</label>
            <div class="input">
              <div class="input-prepend">
                <span class="add-on">@</span>
                <input class="medium" id="prependedInput" name="prependedInput" size="16" type="text" />
              </div>
              <span class="help-block">Here's some help text</span>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label for="prependedInput2">Prepended checkbox</label>
            <div class="input">
              <div class="input-prepend">
                <label class="add-on"><input type="checkbox" name="" id="" value="" /></label>
                <input class="mini" id="prependedInput2" name="prependedInput2" size="16" type="text" />
              </div>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label for="appendedInput">Appended checkbox</label>
            <div class="input">
              <div class="input-append">
                <input class="mini" id="appendedInput" name="appendedInput" size="16" type="text" />
                <label class="add-on active"><input type="checkbox" name="" id="" value="" checked="checked" /></label>
              </div>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label for="fileInput">File input</label>
            <div class="input">
              <input class="input-file" id="fileInput" name="fileInput" type="file" />
            </div>
          </div><!-- /clearfix -->
        </fieldset>
        <fieldset>
          <legend>Example form legend</legend>
          <div class="clearfix">
            <label id="optionsCheckboxes">List of options</label>
            <div class="input">
              <ul class="inputs-list">
                <li>
                  <label>
                    <input type="checkbox" name="optionsCheckboxes" value="option1" />
                    <span>Option one is this and that&mdash;be sure to include why it’s great</span>
                  </label>
                </li>
                <li>
                  <label>
                    <input type="checkbox" name="optionsCheckboxes" value="option2" />
                    <span>Option two can also be checked and included in form results</span>
                  </label>
                </li>
                <li>
                  <label>
                    <input type="checkbox" name="optionsCheckboxes" value="option2" />
                    <span>Option three can&mdash;yes, you guessed it&mdash;also be checked and included in form results. Let's make it super long so that everyone can see how it wraps, too.</span>
                  </label>
                </li>
                <li>
                  <label class="disabled">
                    <input type="checkbox" name="optionsCheckboxes" value="option2" disabled />
                    <span>Option four cannot be checked as it is disabled.</span>
                  </label>
                </li>
              </ul>
              <span class="help-block">
                <strong>Note:</strong> Labels surround all the options for much larger click areas and a more usable form.
              </span>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label>Date range</label>
            <div class="input">
              <div class="inline-inputs">
                <input class="small" type="text" value="May 1, 2011" />
                <input class="mini" type="text" value="12:00am" />
                to
                <input class="small" type="text" value="May 8, 2011" />
                <input class="mini" type="text" value="11:59pm" />
                <span class="help-block">All times are shown as Pacific Standard Time (GMT -08:00).</span>
              </div>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label for="textarea">Textarea</label>
            <div class="input">
              <textarea class="xxlarge" id="textarea2" name="textarea2" rows="3"></textarea>
              <span class="help-block">
                Block of help text to describe the field above if need be.
              </span>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label id="optionsRadio">List of options</label>
            <div class="input">
              <ul class="inputs-list">
                <li>
                  <label>
                    <input type="radio" checked name="optionsRadios" value="option1" />
                    <span>Option one is this and that&mdash;be sure to include why it’s great</span>
                  </label>
                </li>
                <li>
                  <label>
                    <input type="radio" name="optionsRadios" value="option2" />
                    <span>Option two can is something else and selecting it will deselect options 1</span>
                  </label>
                </li>
              </ul>
            </div>
          </div><!-- /clearfix -->
          <div class="actions">
            <input type="submit" class="btn primary" value="Save changes">&nbsp;<button type="reset" class="btn">Cancel</button>
          </div>
        </fieldset>
      </form>
    </div>
  </div><!-- /row -->

  <br />

  <div class="row">
    <div class="span4">
      <h2>Stacked forms</h2>
      <p>Add <code>.form-stacked</code> to your form’s HTML and you’ll have labels on top of their fields instead of to their left. This works great if your forms are short or you have two columns of inputs for heavier forms.</p>
    </div>
    <div class="span12">
      <form action="" class="form-stacked">
        <fieldset>
          <legend>Example form legend</legend>
          <div class="clearfix">
            <label for="xlInput3">X-Large input</label>
            <div class="input">
              <input class="xlarge" id="xlInput3" name="xlInput3" size="30" type="text" />
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label for="stackedSelect">Select</label>
            <div class="input">
              <select name="stackedSelect" id="stackedSelect">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
              </select>
            </div>
          </div><!-- /clearfix -->
        </fieldset>
        <fieldset>
          <legend>Example form legend</legend>
          <div class="clearfix error">
            <label for="xlInput4">X-Large input</label>
            <div class="input">
              <input class="xlarge error" id="xlInput4" name="xlInput4" size="30" type="text" />
              <span class="help-inline">Small snippet of help text</span>
            </div>
          </div><!-- /clearfix -->
          <div class="clearfix">
            <label id="optionsCheckboxes">List of options</label>
            <div class="input">
              <ul class="inputs-list">
                <li>
                  <label>
                    <input type="checkbox" name="optionsCheckboxes" value="option1" />
                    <span>Option one is this and that&mdash;be sure to include why it’s great</span>
                  </label>
                </li>
                <li>
                  <label>
                    <input type="checkbox" name="optionsCheckboxes" value="option2" />
                    <span>Option two can also be checked and included in form results</span>
                  </label>
                </li>
              </ul>
              <span class="help-block">
                <strong>Note:</strong> Labels surround all the options for much larger click areas and a more usable form.
              </span>
            </div>
          </div><!-- /clearfix -->
        </fieldset>
        <div class="actions">
          <button type="submit" class="btn primary">Save changes</button>&nbsp;<button type="reset" class="btn">Cancel</button>
        </div>
      </form>
    </div>
  </div><!-- /row -->

  <div class="row">
    <div class="span4">
      <h2>Form field sizes</h2>
      <p>Customize any form <code>input</code>, <code>select</code>, or <code>textarea</code> width by adding just a few classes to your markup.</p>
      <p>As of v1.3.0, we have added the grid-based sizing classes for form elements. <strong>Please use the these over the existing <code>.mini</code>, <code>.small</code>, etc classes.</strong></p>
    </div>
    <div class="span12">
      <form action="">
        <div class="clearfix"><input class="span2" id="" name="" type="text" placeholder=".span2" /></div>
        <div class="clearfix"><input class="span3" id="" name="" type="text" placeholder=".span3" /></div>
        <div class="clearfix"><input class="span4" id="" name="" type="text" placeholder=".span4" /></div>
        <div class="clearfix"><input class="span5" id="" name="" type="text" placeholder=".span5" /></div>
        <div class="clearfix"><input class="span6" id="" name="" type="text" placeholder=".span6" /></div>
        <div class="clearfix"><input class="span7" id="" name="" type="text" placeholder=".span7" /></div>
        <div class="clearfix"><input class="span8" id="" name="" type="text" placeholder=".span8" /></div>
        <div class="clearfix"><input class="span9" id="" name="" type="text" placeholder=".span9" /></div>
        <div class="clearfix"><input class="span10" id="" name="" type="text" placeholder=".span10" /></div>
        <div class="clearfix"><input class="span11" id="" name="" type="text" placeholder=".span11" /></div>
        <div class="clearfix"><input class="span12" id="" name="" type="text" placeholder=".span12" /></div>
      </form>
    </div>
  </div>

  <div class="row">
    <div class="span4">
      <h2>Buttons</h2>
      <p>As a convention, buttons are used for actions while links are used for objects. For instance, "Download" could be a button and "recent activity" could be a link.</p>
      <p>All buttons default to a light gray style, but a number of functional classes can be applied for different color styles. These classes include a blue <code>.primary</code> class, a light-blue <code>.info</code> class, a green <code>.success</code> class, and a red <code>.danger</code> class.</p>
    </div>
    <div class="span12">
      <h3>Example buttons</h3>
      <p>Button styles can be applied to anything with the <code>.btn</code> applied. Typically you’ll want to apply these to only <code>&lt;a&gt;</code>, <code>&lt;button&gt;</code>, and select <code>&lt;input&gt;</code> elements. Here’s how it looks:</p>
      <div class="well" style="padding: 14px 19px;">
        <button class="btn primary">Primary</button>&nbsp;<button class="btn">Default</button>&nbsp;<button class="btn info">Info</button>&nbsp;<button class="btn success">Success</button>&nbsp;<button class="btn danger">Danger</button>
      </div>
      <h3>Alternate sizes</h3>
      <p>Fancy larger or smaller buttons? Have at it!</p>
      <div class="well">
        <a href="#" class="btn large primary">Primary action</a>
        <a href="#" class="btn large">Action</a>
      </div>
      <div class="well" style="padding: 16px 19px;">
        <a href="#" class="btn small primary">Primary action</a>
        <a href="#" class="btn small">Action</a>
      </div>
      <h3>Disabled state</h3>
      <p>For buttons that are not active or are disabled by the app for one reason or another, use the disabled state. That’s <code>.disabled</code> for links and <code>:disabled</code> for <code>&lt;button&gt;</code> elements.</p>
      <h4>Links</h4>
      <div class="well">
        <a href="#" class="btn large primary disabled">Primary action</a>
        <a href="#" class="btn large disabled">Action</a>
      </div>
      <h4>Buttons</h4>
      <div class="well">
        <button class="btn large primary disabled" disabled="disabled">Primary action</button>&nbsp;<button class="btn large" disabled>Action</button>
      </div>
    </div>
  </div><!-- /row -->
</section>

      </div><!-- /container -->


    <footer class="footer">
      <div class="container">
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>
          Designed and built with all the love in the world <a href="http://twitter.com/twitter" target="_blank">@twitter</a> by <a href="http://twitter.com/mdo" target="_blank">@mdo</a> and <a href="http://twitter.com/fat" target="_blank">@fat</a>.<br />
          Code licensed under the <a href="http://www.apache.org/licenses/LICENSE-2.0" target="_blank">Apache License v2.0</a>. Documentation licensed under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.
        </p>
      </div>
    </footer>

    <!-- Le javascript -->
    <script src="/assets/application-d2264b1de89579d97d667de6fc53e43e.js" type="text/javascript"></script>
    <script src="/assets/bootstrap-doc/bootstrap-doc-41b337a607c2dc506a7052e9dfbd8427.js" type="text/javascript"></script>

  </body>
</html>

