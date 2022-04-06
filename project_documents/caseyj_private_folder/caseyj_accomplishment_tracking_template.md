# Casey Jones - Lucas and the Willies Accomplishment Tracking

Each team member should have their own version of this document.

## Background

It's a great habit to record accomplishments and progress throughout your SDE
career. It's useful to reflect on what you've worked on in the past and comes in
handy during performance reviews and promotion cycles.

## Instructions

**Save a copy of this document in your “private” folder.**

Using the below template, keep track of what you’ve worked on each week during
the unit. 1-3 bullets under each section for each week should suffice. This
should only take 5 - 10 minutes of reflection each week.

As you track your work, think about how it relates to the SDE fundamental skills
laid out in the syllabus and how you are practicing them.

* Converts a design into code and delivers it using best practices
* Writes secure, testable, maintainable code
* Understands when to use (or not) a broad range of data structures and
  algorithms
* Creates unit tests that thoroughly test functionality
* Troubleshoots by debugging and reviewing errors, logfiles, and metrics
* Contributes to planning and design
* Escalates when projects hit roadblocks and risks

The important work samples don’t only include the things you authored, but
should include things like key CRs you reviewed that you are proud of as well!

_You will submit your completed Accomplishment Tracking Document to your
instructors by the end of the unit._

## Week 1

**Goals:**

Initial FrontEnd Design discussion and implementation

**Activity:**

Mock FrontEnd Design for iteration

**Important Docs, Commits, or Code Reviews**:

Initial FrontEnd commit of index.html and associated scripts

**Things learned:**

Usage of QuerySelector over getElement

## Week 2

**Goals:**

Iterate on FrontEnd html and javascript
Prepare FrontEnd for API integration

**Activity:**

More FrontEnd iteration on Design
Created placeholder functions for API integration

**Important Docs, Commits, or Code Reviews**:

Segregated scripts/css/resources/images into subfolders for clarity

Created a class-based approach to FrontEnd Design with MenuItem and Cart classes, 
as well as a class combining certain input elements

**Things learned:**

Javascript is still awful, and == is a loose (truthy/falsey) comparison, strict comparisons require ===

## Week 3

**Goals:**

Finish FrontEnd functionality implementation

**Activity:**

Rewrite entire frontend codebase to use innerHTML for clarity,
to track down and fix a bug with context/handler loss from the class-based approach

**Important Docs, Commits, or Code Reviews**:

Full rewrite of index.html and init and completion of admin.html

**Things learned:**

You need to be careful with creating elements in a loop,
as when an element is added to the DOM it can cause some context/handler loss
a new class reference is done in the same loop

## Week 4

**Goals:**

Finalize css design

**Activity:**

Implement Grid and Flexbox attributes to style programmatic elements appropriately

Apply general styling

**Important Docs, Commits, or Code Reviews**:

Full styling commit

**Things learned:**

Testing for transparency during design should be considered

Being a colorblind UI designer sucks