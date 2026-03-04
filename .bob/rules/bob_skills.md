# Bob Skills - Dynamic Loading System

## ⚠️ MANDATORY SKILL DETECTION PROTOCOL

**CRITICAL: You MUST follow this protocol for EVERY user request:**

1. **STOP** - Do not proceed with any task until you complete skill detection
2. **SCAN** - Check the user's prompt against the Skill Keyword Index below
3. **MATCH** - If ANY keyword matches, that skill MUST be loaded
4. **LOAD** - Immediately read `.bob/skills/{skill-name}/SKILL.md` using read_file tool
5. **FOLLOW** - Execute the skill's workflow completely before proceeding
6. **APPLY** - Use the skill's formatting, best practices, and output requirements

**Failure to detect and load matching skills will result in incomplete, low-quality responses.**

## How It Works

All skills in `.bob/skills/` are available for automatic use. You MUST:
1. **ALWAYS** detect which skill is needed based on the user's request
2. **IMMEDIATELY** load the skill's instructions from `.bob/skills/{skill-name}/SKILL.md`
3. **COMPLETELY** apply the skill to complete the task

## Skill Keyword Index

**MANDATORY: Scan EVERY user prompt against these keywords BEFORE starting any work.**

### algorithmic-art
**Keywords:** algorithmic-art
**Description:** Generate algorithmic art using p5.js
**Triggers:** "use algorithmic-art"

### brand-guidelines
**Keywords:** brand-guidelines
**Description:** Brand consistency enforcement
**Triggers:** "use brand-guidelines"

### canvas-design
**Keywords:** canvas-design
**Description:** Create visual designs and posters
**Triggers:** "use canvas-design"

### doc-coauthoring
**Keywords:** doc-coauthoring
**Description:** Collaborative document editing workflows
**Triggers:** "use doc-coauthoring"

### docx
**Keywords:** docx
**Description:** Create, edit, and analyze Word documents (.docx files)
**Triggers:** "use docx"

### frontend-design
**Keywords:** frontend-design
**Description:** Build frontend interfaces and web components
**Triggers:** "use frontend-design"

### internal-comms
**Keywords:** internal-comms
**Description:** Internal communications templates
**Triggers:** "use internal-comms"

### mcp-builder
**Keywords:** mcp-builder
**Description:** Build Model Context Protocol servers
**Triggers:** "use mcp-builder"

### pdf
**Keywords:** pdf
**Description:** PDF manipulation and extraction
**Triggers:** "use pdf"

### pptx
**Keywords:** pptx
**Description:** PowerPoint presentation creation
**Triggers:** "use pptx"

### skill-creator
**Keywords:** skill-creator
**Description:** Create new custom skills
**Triggers:** "use skill-creator"

### slack-gif-creator
**Keywords:** slack-gif-creator
**Description:** Create animated GIFs for Slack
**Triggers:** "use slack-gif-creator"

### theme-factory
**Keywords:** theme-factory
**Description:** Generate design themes
**Triggers:** "use theme-factory"

### web-artifacts-builder
**Keywords:** web-artifacts-builder
**Description:** Create web artifacts and components
**Triggers:** "use web-artifacts-builder"

### webapp-testing
**Keywords:** webapp-testing
**Description:** Test web applications with Playwright
**Triggers:** "use webapp-testing"

### xlsx
**Keywords:** xlsx
**Description:** Excel spreadsheet operations
**Triggers:** "use xlsx"

## Installed Skills (16 total)

### Creative Skills
- **algorithmic-art** - Generate algorithmic art using p5.js
- **canvas-design** - Create visual designs and posters
- **slack-gif-creator** - Create animated GIFs for Slack
- **theme-factory** - Generate design themes

### Development Skills
- **frontend-design** - Build frontend interfaces and web components
- **mcp-builder** - Build Model Context Protocol servers
- **web-artifacts-builder** - Create web artifacts and components
- **webapp-testing** - Test web applications with Playwright

### Document Skills
- **doc-coauthoring** - Collaborative document editing workflows
- **docx** - Create, edit, and analyze Word documents (.docx files)
- **pdf** - PDF manipulation and extraction
- **pptx** - PowerPoint presentation creation
- **xlsx** - Excel spreadsheet operations

### Enterprise Skills
- **brand-guidelines** - Brand consistency enforcement
- **internal-comms** - Internal communications templates

### Utility Skills
- **skill-creator** - Create new custom skills

## Usage

Simply make requests - Bob automatically uses the right skill:
- "Create a Word document" → docx skill loads automatically
- "Build a landing page" → frontend-design skill loads automatically
- "Create a poster" → canvas-design skill loads automatically
- "Help me create a new skill" → skill-creator skill loads automatically

## Manual Skill Invocation (Optional)

Explicitly invoke a skill when needed:
- `@skill-name your request`
- Example: `@canvas-design create a poster for our team event`

## Skill Detection Algorithm

When processing a user request:
1. **Scan the prompt** for keywords from the Skill Keyword Index
2. **Match keywords** to find the most relevant skill(s)
3. **Load the skill** by reading `.bob/skills/{skill-name}/SKILL.md`
4. **Follow the skill's workflow** from start to finish
5. **Apply skill-specific formatting** and best practices

**Priority Rules:**
- Exact keyword matches take precedence
- Multiple keyword matches increase confidence
- Context matters - consider the full request
- When in doubt, load the skill and verify

## Managing Skills

```bash
# List installed skills
bob-skills list

# Show skill details
bob-skills info docx

# Update all skills
bob-skills update

# Uninstall specific skills
bob-skills uninstall algorithmic-art
```

## Context Management

Skills are loaded on-demand to optimize context window usage:
- Only relevant skills are loaded per request
- Reduces token usage and improves response speed
- All skills remain available when needed

## License Information

Some skills have different licenses:
- **Proprietary skills** (docx, pdf, xlsx, pptx): See individual LICENSE.txt files
- **Open source skills**: Apache 2.0 license

By using these skills, you agree to their respective license terms.

---

*This configuration was auto-generated by bob-skills-installer*
